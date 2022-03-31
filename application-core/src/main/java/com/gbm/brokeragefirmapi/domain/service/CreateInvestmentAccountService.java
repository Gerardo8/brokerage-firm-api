package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountUseCase;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateInvestmentAccountService implements CreateInvestmentAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account createInvestmentAccount(final Account account) {

        return this.accountRepository.createAccount(account);
    }
}
