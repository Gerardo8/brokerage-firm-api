package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountServicePort;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateInvestmentAccountService implements CreateInvestmentAccountServicePort {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public Account createInvestmentAccount(final Account account) {

        return this.accountRepositoryPort.createAccount(account);
    }
}
