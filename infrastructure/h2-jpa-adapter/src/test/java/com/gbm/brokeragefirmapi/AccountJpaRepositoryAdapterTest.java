package com.gbm.brokeragefirmapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.factory.AccountJpaMockFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AccountJpaRepositoryAdapterTest {

    private AccountJpaRepositoryAdapter accountJpaRepositoryAdapter;

    @Mock
    private AccountJpaRepository accountJpaRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.accountJpaRepositoryAdapter = new AccountJpaRepositoryAdapter(this.accountJpaRepository);
    }

    @Test
    void createAccount_shouldReturnCreatedAccount_whenAccountIsSaved() {

        final var account = this.accountJpaRepositoryAdapter.createAccount(createMockAccount());

        assertThat(account).isNotNull();

        verify(this.accountJpaRepository).save(any(AccountJpaEntity.class));

    }

    @Test
    void findAccountById_shouldReturnAccount_whenThereIsAccount() {

        when(this.accountJpaRepository.findById(ACCOUNT_ID))
                .thenReturn(Optional.of(crateMockAccountJpaEntity()));

        final var accountById = this.accountJpaRepositoryAdapter.findAccountById(ACCOUNT_ID)
                .orElse(null);

        assertThat(accountById).isNotNull();
        assertThat(accountById.getId()).isNotNull().isPositive();
        assertThat(accountById.getCash()).isNotNull();

        verify(this.accountJpaRepository).findById(ACCOUNT_ID);
    }
}