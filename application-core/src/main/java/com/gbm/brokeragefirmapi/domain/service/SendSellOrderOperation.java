package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.CurrentBalance;
import com.gbm.brokeragefirmapi.domain.model.Stock;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import com.gbm.brokeragefirmapi.port.secondary.IssuerRepositoryPort;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.INSUFFICIENT_STOCKS;
import static java.util.Collections.singletonList;

@RequiredArgsConstructor
public class SendSellOrderOperation implements SendOrderOperation {

    private final OrderRepositoryPort orderRepositoryPort;
    private final IssuerRepositoryPort issuerRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public ProcessedOrder sendOrder(final Order order, final Stock stock, final Account account) {

        final var optionalIssuer = this.issuerRepositoryPort
                .findByAccountIdAndStockId(account.getId(), stock.getId());

        if (optionalIssuer.isPresent()) {

            final var issuer = optionalIssuer.get();
            final var totalShares = issuer.getTotalShares() - order.getTotalShares();

            if (totalShares < 0) {

                return new ProcessedOrder(new CurrentBalance(account.getCash(), new ArrayList<>()), singletonList(INSUFFICIENT_STOCKS));
            }

            final var totalAmount = stock.getSharePrice().multiply(BigDecimal.valueOf(order.getTotalShares()));
            final var newCash = account.getCash().add(totalAmount);

            account.setCash(newCash);
            order.setAccount(account);
            this.accountRepositoryPort.createAccount(account);
            this.orderRepositoryPort.createOrder(order);

            issuer.setTotalShares(totalShares);

            this.issuerRepositoryPort.createIssuer(issuer);

            final var issuers = this.issuerRepositoryPort.findAllByAccountId(account.getId());

            return new ProcessedOrder(new CurrentBalance(newCash, issuers), new ArrayList<>());
        }

        return new ProcessedOrder(new CurrentBalance(account.getCash(), new ArrayList<>()), singletonList(INSUFFICIENT_STOCKS));
    }
}