package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation.BUY;
import static com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation.SELL;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.LocalTime.NOON;

public class OrderMockFactory {

    public static final Integer TOTAL_SHARES = 2;
    public static final String ISSUER_NAME = "AAPL";
    public static final BigDecimal SHARE_PRICE = BigDecimal.valueOf(80L);
    public static final LocalDateTime TIMESTAMP = LocalDateTime.of(LocalDate.now(), NOON);
    public static final LocalDateTime INVALID_TIMESTAMP = LocalDateTime.of(LocalDate.now(), MIDNIGHT);

    public static Order createMockBuyOrder() {

        return Order.builder()
                .totalShares(TOTAL_SHARES)
                .timestamp(TIMESTAMP)
                .operation(BUY)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .account(createMockAccount())
                .build();
    }

    public static Order createMockSellOrder() {

        return Order.builder()
                .totalShares(TOTAL_SHARES)
                .timestamp(TIMESTAMP)
                .operation(SELL)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .account(createMockAccount())
                .build();
    }

    public static Order createMockOrderWithInvalidTimestamp() {

        return Order.builder()
                .totalShares(TOTAL_SHARES)
                .timestamp(INVALID_TIMESTAMP)
                .operation(BUY)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .account(createMockAccount())
                .build();
    }
}
