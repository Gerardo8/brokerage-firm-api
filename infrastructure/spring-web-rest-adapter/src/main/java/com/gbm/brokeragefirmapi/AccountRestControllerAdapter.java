package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountUseCase;
import com.gbm.brokeragefirmapi.port.primary.SendOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.gbm.brokeragefirmapi.CreateAccountRestMapper.accountFrom;
import static com.gbm.brokeragefirmapi.CreateAccountRestMapper.createAccountResponseFrom;
import static com.gbm.brokeragefirmapi.SendOrderRestMapper.orderFrom;
import static com.gbm.brokeragefirmapi.SendOrderRestMapper.sendOrderResponseFrom;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountRestControllerAdapter {

    private final CreateInvestmentAccountUseCase createInvestmentAccountUseCase;
    private final SendOrderUseCase sendOrderUseCase;

    @PostMapping("/accounts")
    @ResponseStatus(CREATED)
    public CreateAccountResponse createInvestmentAccount(final @Valid @RequestBody CreateAccountRequest createAccountRequest) {

        final Account account = this.createInvestmentAccountUseCase
                .createInvestmentAccount(accountFrom(createAccountRequest));

        return createAccountResponseFrom(account);
    }

    @PostMapping("accounts/{id}/orders")
    public SendOrderResponse sendOrder(final @PathVariable Long id, final @Valid @RequestBody SendOrderRequest sendOrderRequest) {

        final var processedOrder = this.sendOrderUseCase.sendOrder(orderFrom(sendOrderRequest, id));

        return sendOrderResponseFrom(processedOrder);
    }
}
