package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateAccountRestMapper {

    public static Account accountFrom(final CreateAccountRequest createAccountRequest) {

        return new Account(createAccountRequest.getCash());
    }

    public static CreateAccountResponse createAccountResponseFrom(final Account account) {

        final var issuers = account.getIssuers()
                .stream()
                .map(CreateAccountRestMapper::createIssuerDtoFrom)
                .collect(toList());

        return CreateAccountResponse.builder()
                .id(account.getId())
                .cash(account.getCash())
                .issuers(issuers)
                .build();
    }

    public static IssuerDto createIssuerDtoFrom(final Issuer issuer) {

        return IssuerDto.builder()
                .issuerName(issuer.getStock().issuerName())
                .sharePrice(issuer.getStock().sharePrice())
                .totalShares(issuer.getTotalShares())
                .build();
    }

}
