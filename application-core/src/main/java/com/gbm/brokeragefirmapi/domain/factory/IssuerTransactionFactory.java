package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction;
import com.gbm.brokeragefirmapi.domain.model.Order;

import static java.util.concurrent.TimeUnit.MINUTES;

public class IssuerTransactionFactory {

    private IssuerTransactionFactory() {
    }

    public static String createIssuerTransactionId(final Order order) {

        return String.format("%s::%s::%s", order.getOperation(), order.getIssuerName(), order.getTotalShares());
    }

    public static IssuerTransaction createIssuerTransaction(final Order order) {

        return new IssuerTransaction(
                createIssuerTransactionId(order),
                order.getIssuerName(),
                order.getTotalShares(),
                order.getSharePrice(),
                order.getTimestamp(),
                order.getOperation(),
                MINUTES.toMillis(5L)
        );
    }
}
