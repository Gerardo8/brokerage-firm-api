package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountFrom;
import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountJpaEntityFrom;

@Transactional
@RequiredArgsConstructor
public class AccountJpaRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account createAccount(final Account account) {

        final AccountJpaEntity accountJpaEntity = accountJpaEntityFrom(account);
        this.accountJpaRepository.save(accountJpaEntity);

        return accountFrom(accountJpaEntity);
    }

    @Override
    public Optional<Account> findAccountById(final Long id) {

        return this.accountJpaRepository.findById(id)
                .map(AccountJpaMapper::accountFrom);
    }
}
