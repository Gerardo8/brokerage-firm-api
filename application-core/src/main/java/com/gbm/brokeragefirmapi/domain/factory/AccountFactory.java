package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;

public class AccountFactory {

    public static Account createDefaultAccount() {

        return new Account(BigDecimal.ZERO, emptyList());
    }
}
