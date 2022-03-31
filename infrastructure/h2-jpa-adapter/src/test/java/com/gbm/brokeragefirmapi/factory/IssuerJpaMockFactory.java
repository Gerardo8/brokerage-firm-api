package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.IssuerJpaEntity;
import com.gbm.brokeragefirmapi.domain.model.Issuer;

import java.util.List;

import static com.gbm.brokeragefirmapi.factory.AccountJpaMockFactory.crateMockAccountJpaEntity;
import static com.gbm.brokeragefirmapi.factory.AccountJpaMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.factory.OrderJpaMockFactory.TOTAL_SHARES;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.createMockStock;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.createMockStockJpaEntity;
import static java.util.Collections.singletonList;

public class IssuerJpaMockFactory {

    public static final Integer ISSUER_ID = 1;

    public static IssuerJpaEntity createMockIssuerJpaEntity() {

        return IssuerJpaEntity.builder()
                .id(ISSUER_ID)
                .account(crateMockAccountJpaEntity())
                .stock(createMockStockJpaEntity())
                .totalShares(TOTAL_SHARES)
                .build();
    }

    public static Issuer createMockIssuer() {

        return Issuer.builder()
                .id(ISSUER_ID)
                .account(createMockAccount())
                .stock(createMockStock())
                .totalShares(TOTAL_SHARES)
                .build();
    }

    public static List<IssuerJpaEntity> createMockIssuerJpaEntityList() {

        return singletonList(createMockIssuerJpaEntity());
    }
}
