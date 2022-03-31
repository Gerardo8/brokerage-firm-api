package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.StockJpaEntity;
import com.gbm.brokeragefirmapi.domain.model.Stock;

import java.math.BigDecimal;

public class StockJpaMockFactory {

    public static final Integer STOCK_ID = 1;
    public static final String ISSUER_NAME = "AAPL";
    public static final BigDecimal SHARE_PRICE = BigDecimal.valueOf(80L);

    public static StockJpaEntity createMockStockJpaEntity() {

        return StockJpaEntity.builder()
                .id(STOCK_ID)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .build();
    }

    public static Stock createMockStock() {

        return Stock.builder()
                .id(STOCK_ID)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .build();
    }
}
