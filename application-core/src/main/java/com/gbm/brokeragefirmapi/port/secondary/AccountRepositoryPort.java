package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Account;

public interface AccountRepositoryPort {

    Account createAccount(Account account);

}
