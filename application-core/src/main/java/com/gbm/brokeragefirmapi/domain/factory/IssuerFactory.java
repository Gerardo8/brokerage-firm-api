package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.Stock;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IssuerFactory {

    public static Issuer createIssuerFrom(final Account account, final Stock stock, final Order order) {

        return Issuer.builder()
                .totalShares(order.getTotalShares())
                .stock(stock)
                .account(account)
                .build();
    }

    public static Issuer createIssuerWithTotalShares(final Issuer issuer, final Integer totalShares) {

        return Issuer.builder()
                .id(issuer.getId())
                .totalShares(issuer.getTotalShares() + totalShares)
                .stock(issuer.getStock())
                .account(issuer.getAccount())
                .build();
    }
}
