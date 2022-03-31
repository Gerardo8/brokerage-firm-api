package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.CurrentBalance;
import com.gbm.brokeragefirmapi.domain.model.Stock;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepository;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.gbm.brokeragefirmapi.domain.factory.IssuerTransactionFactory.createIssuerTransaction;
import static com.gbm.brokeragefirmapi.domain.factory.ProcessedOrderFactory.createFailedProcessedOrder;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.INSUFFICIENT_STOCKS;

@RequiredArgsConstructor
public class SendSellOrderOperation implements SendOrderOperation {

    private final OrderRepository orderRepository;
    private final IssuerRepository issuerRepository;
    private final AccountRepository accountRepository;
    private final IssuerTransactionRepository issuerTransactionRepository;

    @Override
    public ProcessedOrder sendOrder(final Order order, final Stock stock, final Account account) {

        final var optionalIssuer = this.issuerRepository
                .findByAccountIdAndStockId(account.getId(), stock.getId());

        if (optionalIssuer.isPresent()) {

            final var issuer = optionalIssuer.get();
            final var totalShares = issuer.getTotalShares() - order.getTotalShares();

            if (totalShares < 0) {

                return createFailedProcessedOrder(account, INSUFFICIENT_STOCKS);
            }

            final var totalAmount = stock.getSharePrice().multiply(BigDecimal.valueOf(order.getTotalShares()));
            final var newCash = account.getCash().add(totalAmount);

            account.setCash(newCash);
            order.setAccount(account);
            this.accountRepository.createAccount(account);
            this.orderRepository.createOrder(order);

            issuer.setTotalShares(totalShares);

            this.issuerRepository.createIssuer(issuer);
            this.issuerTransactionRepository.createIssuerTransaction(createIssuerTransaction(order));

            final var issuers = this.issuerRepository.findAllByAccountId(account.getId());

            return new ProcessedOrder(new CurrentBalance(newCash, issuers), new ArrayList<>());
        }

        return createFailedProcessedOrder(account, INSUFFICIENT_STOCKS);
    }
}
