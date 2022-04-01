package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction;
import com.gbm.brokeragefirmapi.domain.model.Order;

import static com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation.BUY;

public class IssuerTransactionMockFactory {

    public static IssuerTransaction createMockIssuerTransaction(final Order order) {

        return IssuerTransaction.builder()
                .id(IssuerTransactionFactory.createIssuerTransactionId(order))
                .issuerName(order.getIssuerName())
                .sharePrice(order.getSharePrice())
                .timestamp(order.getTimestamp())
                .totalShares(order.getTotalShares())
                .operation(BUY)
                .build();
    }
}
