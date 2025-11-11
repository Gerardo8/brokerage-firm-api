package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountJpaMapper {

    public static Account accountFrom(final AccountJpaEntity accountJpaEntity) {

        return new Account(
                accountJpaEntity.getId(),
                accountJpaEntity.getCash(),
                new ArrayList<>()
        );
    }

    public static AccountJpaEntity accountJpaEntityFrom(final Account account) {

        return new AccountJpaEntity(account.getId(), account.getCash());
    }

}
