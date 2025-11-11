package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Issuer;
import lombok.NoArgsConstructor;

import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountFrom;
import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountJpaEntityFrom;
import static com.gbm.brokeragefirmapi.StockJpaMapper.stockFrom;
import static com.gbm.brokeragefirmapi.StockJpaMapper.stockJpaEntityFrom;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IssuerJpaMapper {

    public static Issuer issuerFrom(final IssuerJpaEntity issuerJpaEntity) {

        return new Issuer(
                issuerJpaEntity.getId(),
                issuerJpaEntity.getTotalShares(),
                accountFrom(issuerJpaEntity.getAccount()),
                stockFrom(issuerJpaEntity.getStock())
        );
    }

    public static IssuerJpaEntity issuerJpaEntityFrom(final Issuer issuer) {

        return IssuerJpaEntity.builder()
                .id(issuer.getId())
                .totalShares(issuer.getTotalShares())
                .account(accountJpaEntityFrom(issuer.getAccount()))
                .stock(stockJpaEntityFrom(issuer.getStock()))
                .build();
    }
}
