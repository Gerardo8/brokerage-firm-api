package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountJpaMapper {

    public static Account accountFrom(final AccountJpaEntity accountJpaEntity) {

        return Account
                .builder()
                .id(accountJpaEntity.getId())
                .cash(accountJpaEntity.getCash())
                .issuers(new ArrayList<>())
                .build();
    }

    public static AccountJpaEntity accountJpaEntityFrom(final Account account) {

        return new AccountJpaEntity(account.getId(), account.getCash());
    }

}
