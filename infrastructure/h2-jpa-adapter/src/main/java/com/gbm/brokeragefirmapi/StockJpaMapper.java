package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Stock;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StockJpaMapper {

    public static Stock stockFrom(final StockJpaEntity stockJpaEntity) {

        return Stock.builder()
                .id(stockJpaEntity.getId())
                .issuerName(stockJpaEntity.getIssuerName())
                .sharePrice(stockJpaEntity.getSharePrice())
                .build();
    }

    public static StockJpaEntity stockJpaEntityFrom(final Stock stock) {

        return StockJpaEntity.builder()
                .id(stock.getId())
                .issuerName(stock.getIssuerName())
                .sharePrice(stock.getSharePrice())
                .build();
    }
}
