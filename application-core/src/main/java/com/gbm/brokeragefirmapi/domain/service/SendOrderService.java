package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.port.primary.SendOrderServicePort;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepositoryPort;
import com.gbm.brokeragefirmapi.port.secondary.StockRepositoryPort;
import lombok.RequiredArgsConstructor;

import static com.gbm.brokeragefirmapi.domain.factory.IssuerTransactionFactory.createIssuerTransactionId;
import static com.gbm.brokeragefirmapi.domain.factory.ProcessedOrderFactory.createFailedProcessedOrder;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.CLOSE_MARKET;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.DUPLICATE_OPERATION;
import static com.gbm.brokeragefirmapi.utils.SendOrderConstants.SIX_AM;
import static com.gbm.brokeragefirmapi.utils.SendOrderConstants.THREE_PM;

@RequiredArgsConstructor
public class SendOrderService implements SendOrderServicePort {

    private final AccountRepositoryPort accountRepositoryPort;
    private final StockRepositoryPort stockRepositoryPort;
    private final IssuerTransactionRepositoryPort issuerTransactionRepositoryPort;
    private final SendBuyOrderOperation sendBuyOrderOperation;
    private final SendSellOrderOperation sendSellOrderOperation;

    @Override
    public ProcessedOrder sendOrder(final Order order) {

        final var time = order.getTimestamp().toLocalTime();

        final var accountById = this.accountRepositoryPort
                .findAccountById(order.getAccount().getId())
                .orElseThrow();

        if (!time.isAfter(SIX_AM) || !time.isBefore(THREE_PM)) {

            return createFailedProcessedOrder(accountById, CLOSE_MARKET);
        }

        if (this.issuerTransactionRepositoryPort.findById(createIssuerTransactionId(order)).isPresent()) {

            return createFailedProcessedOrder(accountById, DUPLICATE_OPERATION);
        }

        final var stock = this.stockRepositoryPort
                .findByIssuerName(order.getIssuerName())
                .orElseThrow();

        order.setIssuerName(stock.getIssuerName());
        order.setSharePrice(stock.getSharePrice());

        return switch (order.getOperation()) {
            case BUY -> this.sendBuyOrderOperation.sendOrder(order, stock, accountById);
            case SELL -> this.sendSellOrderOperation.sendOrder(order, stock, accountById);
        };

    }

}
