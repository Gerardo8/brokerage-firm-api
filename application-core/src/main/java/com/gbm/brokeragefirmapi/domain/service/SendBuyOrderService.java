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

@RequiredArgsConstructor
public class SendBuyOrderService {

    private final OrderRepositoryPort orderRepositoryPort;
    private final IssuerRepositoryPort issuerRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public ProcessedOrder sendOrder(final Order order, final Stock stock, final Account accountById) {

        final var processedOrder = new ProcessedOrder();

        final var totalAmount = stock.getSharePrice().multiply(BigDecimal.valueOf(order.getTotalShares()));
        final var newCash = accountById.getCash().subtract(totalAmount);

        if (newCash.intValue() >= 0) {

            accountById.setCash(newCash);
            order.setAccount(accountById);
            this.accountRepositoryPort.createAccount(accountById);
            this.orderRepositoryPort.createOrder(order);

            final var optionalIssuer = this.issuerRepositoryPort
                    .findByAccountIdAndStockId(accountById.getId(), stock.getId());

            final var issuer = optionalIssuer.isPresent() ?
                    createIssuerWithTotalShares(optionalIssuer.get(), order.getTotalShares()) :
                    createIssuerFrom(accountById, stock, order);

            this.issuerRepositoryPort.createIssuer(issuer);

            final var issuers = this.issuerRepositoryPort.findAllByAccountId(accountById.getId());

            processedOrder.setCurrentBalance(new CurrentBalance(newCash, issuers));

        } else {

            processedOrder.setCurrentBalance(new CurrentBalance(accountById.getCash(), new ArrayList<>()));
            processedOrder.getBusinessErrors().add(INSUFFICIENT_BALANCE);
        }

        return processedOrder;
    }
}
