package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountServicePort;
import com.gbm.brokeragefirmapi.port.primary.SendOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.gbm.brokeragefirmapi.CreateAccountRestMapper.accountFrom;
import static com.gbm.brokeragefirmapi.CreateAccountRestMapper.createAccountResponseFrom;
import static com.gbm.brokeragefirmapi.SendOrderRestMapper.orderFrom;
import static com.gbm.brokeragefirmapi.SendOrderRestMapper.sendOrderResponseFrom;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountRestControllerAdapter {

    private final CreateInvestmentAccountServicePort createInvestmentAccountServicePort;
    private final SendOrderServicePort sendOrderServicePort;

    @PostMapping("/accounts")
    public CreateAccountResponse createInvestmentAccount(final @Valid @RequestBody CreateAccountRequest createAccountRequest) {

        final Account account = this.createInvestmentAccountServicePort
                .createInvestmentAccount(accountFrom(createAccountRequest));

        return createAccountResponseFrom(account);
    }

    @PostMapping("accounts/{id}/orders")
    public SendOrderResponse sendOrder(final @PathVariable Long id, final @Valid @RequestBody SendOrderRequest sendOrderRequest) {

        final var processedOrder = this.sendOrderServicePort.sendOrder(orderFrom(sendOrderRequest, id));

        return sendOrderResponseFrom(processedOrder);
    }
}
