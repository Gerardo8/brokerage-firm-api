package com.gbm.brokeragefirmapi.port.primary;

import com.gbm.brokeragefirmapi.domain.model.Account;

public interface CreateInvestmentAccountServicePort {

    Account createInvestmentAccount(Account account);
}
