package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountMockFactory {

    public static Account createMockAccount() {

        return Account
                .builder()
                .cash(new BigDecimal(1_000))
                .build();
    }

    public static Account createMockAccountWithId() {

        return Account
                .builder()
                .id(1L)
                .cash(new BigDecimal(1_000))
                .issuers(new ArrayList<>())
                .build();
    }
}
