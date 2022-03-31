package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.domain.model.Order;

import java.time.LocalDateTime;

import static com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation.BUY;
import static com.gbm.brokeragefirmapi.factory.AccountJpaMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.ISSUER_NAME;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.SHARE_PRICE;

public class OrderJpaMockFactory {

    public static final Integer TOTAL_SHARES = 2;
    public static final LocalDateTime TIMESTAMP = LocalDateTime.now();

    public static Order createMockOrder() {

        return Order.builder()
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .account(createMockAccount())
                .operation(BUY)
                .timestamp(TIMESTAMP)
                .totalShares(TOTAL_SHARES)
                .build();
    }
}
