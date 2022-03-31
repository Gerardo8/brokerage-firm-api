package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccountWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class CreateInvestmentAccountServiceTest {

    private CreateInvestmentAccountService createInvestmentAccountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.createInvestmentAccountService = new CreateInvestmentAccountService(this.accountRepository);
    }

    @Test
    void createInvestmentAccount() {

        when(this.accountRepository.createAccount(any(Account.class)))
                .thenReturn(createMockAccountWithId());

        final Account mockAccount = createMockAccount();

        final Account createdAccount = this.createInvestmentAccountService.createInvestmentAccount(mockAccount);

        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount.getId()).isNotNull();
        assertThat(createdAccount.getCash()).isNotNull().isEqualTo(mockAccount.getCash());
        assertThat(createdAccount.getIssuers()).isNotNull().isEmpty();
    }
}