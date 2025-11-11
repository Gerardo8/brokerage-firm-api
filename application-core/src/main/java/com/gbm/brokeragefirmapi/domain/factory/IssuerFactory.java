package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.Stock;

public class IssuerFactory {

    public static Issuer createIssuerFrom(final Account account, final Stock stock, final Order order) {

        return new Issuer(
                order.getTotalShares(),
                account,
                stock
        );
    }

    public static Issuer createIssuerWithTotalShares(final Issuer issuer, final Integer totalShares) {

        return new Issuer(
                issuer.getId(),
                issuer.getTotalShares() + totalShares,
                issuer.getAccount(),
                issuer.getStock()
        );
    }
}
