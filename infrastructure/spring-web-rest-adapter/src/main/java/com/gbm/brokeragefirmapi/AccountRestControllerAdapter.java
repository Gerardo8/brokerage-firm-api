package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gbm.brokeragefirmapi.AccountRestMapper.accountFrom;
import static com.gbm.brokeragefirmapi.AccountRestMapper.createAccountResponseFrom;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountRestControllerAdapter {

    private final CreateInvestmentAccountServicePort createInvestmentAccountServicePort;

    @PostMapping("/accounts")
    public CreateAccountResponse createInvestmentAccount(final @RequestBody CreateAccountRequest createAccountRequest) {

        final Account account = this.createInvestmentAccountServicePort
                .createInvestmentAccount(accountFrom(createAccountRequest));

        return createAccountResponseFrom(account);
    }
}
