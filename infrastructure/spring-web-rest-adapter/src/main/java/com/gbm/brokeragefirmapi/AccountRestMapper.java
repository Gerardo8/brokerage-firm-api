package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountRestMapper {

    public static Account accountFrom(final CreateAccountRequest createAccountRequest) {

        return new Account(createAccountRequest.getCash());
    }

    public static CreateAccountResponse createAccountResponseFrom(final Account account) {

        return CreateAccountResponse.builder()
                .id(account.getId())
                .cash(account.getCash())
                .issuers(account.getIssuers())
                .build();
    }

}
