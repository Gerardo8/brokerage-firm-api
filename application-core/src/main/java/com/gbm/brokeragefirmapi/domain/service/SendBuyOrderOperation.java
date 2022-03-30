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

import static com.gbm.brokeragefirmapi.domain.factory.IssuerFactory.createIssuerFrom;
import static com.gbm.brokeragefirmapi.domain.factory.IssuerFactory.createIssuerWithTotalShares;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.INSUFFICIENT_BALANCE;
import static java.util.Collections.singletonList;

@RequiredArgsConstructor
public class SendBuyOrderOperation implements SendOrderOperation {

    private final OrderRepositoryPort orderRepositoryPort;
    private final IssuerRepositoryPort issuerRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public ProcessedOrder sendOrder(final Order order, final Stock stock, final Account account) {

        final var totalAmount = stock.getSharePrice().multiply(BigDecimal.valueOf(order.getTotalShares()));
        final var newCash = account.getCash().subtract(totalAmount);

        if (newCash.intValue() >= 0) {

            account.setCash(newCash);
            order.setAccount(account);
            this.accountRepositoryPort.createAccount(account);
            this.orderRepositoryPort.createOrder(order);

            final var optionalIssuer = this.issuerRepositoryPort
                    .findByAccountIdAndStockId(account.getId(), stock.getId());

            final var issuer = optionalIssuer.isPresent() ?
                    createIssuerWithTotalShares(optionalIssuer.get(), order.getTotalShares()) :
                    createIssuerFrom(account, stock, order);

            this.issuerRepositoryPort.createIssuer(issuer);

            final var issuers = this.issuerRepositoryPort.findAllByAccountId(account.getId());

            return new ProcessedOrder(new CurrentBalance(newCash, issuers), new ArrayList<>());

        }

        return new ProcessedOrder(new CurrentBalance(account.getCash(), new ArrayList<>()), singletonList(INSUFFICIENT_BALANCE));
    }
}
