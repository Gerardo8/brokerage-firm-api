package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.port.primary.SendOrderServicePort;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import com.gbm.brokeragefirmapi.port.secondary.StockRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendOrderService implements SendOrderServicePort {

    private final AccountRepositoryPort accountRepositoryPort;
    private final StockRepositoryPort stockRepositoryPort;
    private final SendBuyOrderService sendBuyOrderService;

    @Override
    public ProcessedOrder sendOrder(final Order order) {

        final var stock = this.stockRepositoryPort
                .findByIssuerName(order.getIssuerName())
                .orElseThrow();

        order.setIssuerName(stock.getIssuerName());
        order.setSharePrice(stock.getSharePrice());

        final var accountById = this.accountRepositoryPort
                .findAccountById(order.getAccount().getId())
                .orElseThrow();

        return switch (order.getOperation()) {
            case BUY -> this.sendBuyOrderService.sendOrder(order, stock, accountById);
            case SELL -> new ProcessedOrder();
        };

    }

}
