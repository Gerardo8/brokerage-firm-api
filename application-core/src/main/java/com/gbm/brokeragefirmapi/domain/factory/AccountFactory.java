package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountFactory {

    public static Account createDefaultAccount() {

        return Account.builder()
                .cash(BigDecimal.ZERO)
                .issuers(emptyList())
                .build();
    }
}
