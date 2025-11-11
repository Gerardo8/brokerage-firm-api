package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Stock;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StockJpaMapper {

    public static Stock stockFrom(final StockJpaEntity stockJpaEntity) {

        return new Stock(
                stockJpaEntity.getId(),
                stockJpaEntity.getIssuerName(),
                stockJpaEntity.getSharePrice()
        );
    }

    public static StockJpaEntity stockJpaEntityFrom(final Stock stock) {

        return StockJpaEntity.builder()
                .id(stock.id())
                .issuerName(stock.issuerName())
                .sharePrice(stock.sharePrice())
                .build();
    }
}
