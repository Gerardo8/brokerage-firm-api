package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.AccountJpaEntity;
import com.gbm.brokeragefirmapi.domain.model.Account;

import java.math.BigDecimal;

public class AccountJpaMockFactory {

    public static final Long ACCOUNT_ID = 1L;

    public static AccountJpaEntity crateMockAccountJpaEntity() {

        return new AccountJpaEntity(1L, BigDecimal.valueOf(1_000));
    }

    public static Account createMockAccount() {

        return Account.builder()
                .cash(new BigDecimal(1_000))
                .build();
    }
}
