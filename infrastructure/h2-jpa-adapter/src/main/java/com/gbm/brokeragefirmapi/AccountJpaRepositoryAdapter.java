package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountJpaEntityFrom;

@Component
@Transactional
@RequiredArgsConstructor
public class AccountJpaRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account createAccount(final Account account) {

        final AccountJpaEntity accountJpaEntity = accountJpaEntityFrom(account);
        this.accountJpaRepository.save(accountJpaEntity);

        return AccountJpaMapper.accountFrom(accountJpaEntity);
    }
}