package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {

    Account createAccount(Account account);

    Optional<Account> findAccountById(Long id);

}
