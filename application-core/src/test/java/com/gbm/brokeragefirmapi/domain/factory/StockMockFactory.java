package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Stock;

import static com.gbm.brokeragefirmapi.domain.factory.OrderMockFactory.ISSUER_NAME;
import static com.gbm.brokeragefirmapi.domain.factory.OrderMockFactory.SHARE_PRICE;

public class StockMockFactory {

    public static final Integer STOCK_ID = 1;

    public static Stock createMockStock() {

        return Stock.builder()
                .id(STOCK_ID)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .build();
    }

}
