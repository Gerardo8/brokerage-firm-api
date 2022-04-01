package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Issuer;

import java.util.List;

import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.domain.factory.OrderMockFactory.TOTAL_SHARES;
import static com.gbm.brokeragefirmapi.domain.factory.StockMockFactory.createMockStock;
import static java.util.Collections.singletonList;

public class IssuerMockFactory {

    public static final Integer ISSUER_ID = 1;

    public static Issuer createMockIssuer() {

        return Issuer.builder()
                .id(ISSUER_ID)
                .account(createMockAccount())
                .stock(createMockStock())
                .totalShares(TOTAL_SHARES)
                .build();
    }

    public static Issuer createMockIssuerWithoutAvailableStocks() {

        return Issuer.builder()
                .id(ISSUER_ID)
                .account(createMockAccount())
                .stock(createMockStock())
                .totalShares(0)
                .build();
    }

    public static List<Issuer> createMockIssuerWithoutAvailableStocksList() {

        return singletonList(createMockIssuerWithoutAvailableStocks());
    }

    public static List<Issuer> createMockIssuerList() {

        return singletonList(createMockIssuer());
    }
}
