package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction;
import com.gbm.brokeragefirmapi.domain.model.Order;
import lombok.NoArgsConstructor;

import static java.util.concurrent.TimeUnit.MINUTES;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IssuerTransactionFactory {

    public static String createIssuerTransactionId(final Order order) {

        return String.format("%s::%s::%s", order.getOperation(), order.getIssuerName(), order.getTotalShares());
    }

    public static IssuerTransaction createIssuerTransaction(final Order order) {

        return IssuerTransaction.builder()
                .id(createIssuerTransactionId(order))
                .issuerName(order.getIssuerName())
                .totalShares(order.getTotalShares())
                .sharePrice(order.getSharePrice())
                .timestamp(order.getTimestamp())
                .operation(order.getOperation())
                .timeToLive(MINUTES.toMillis(5L))
                .build();
    }
}
