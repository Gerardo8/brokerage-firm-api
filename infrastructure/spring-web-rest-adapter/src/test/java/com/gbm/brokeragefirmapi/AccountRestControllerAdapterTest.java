package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountUseCase;
import com.gbm.brokeragefirmapi.port.primary.SendOrderUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.gbm.brokeragefirmapi.factory.AccountRestControllerMockFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountRestControllerAdapter.class)
class AccountRestControllerAdapterTest {

    private static final String CREATE_ACCOUNT_ENDPOINT = "/api/v1/accounts";
    private static final String SEND_ORDER_ENDPOINT = "/api/v1/accounts/1/orders";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateInvestmentAccountUseCase createInvestmentAccountUseCase;

    @MockBean
    private SendOrderUseCase sendOrderUseCase;

    @Test
    void createInvestmentAccount_shouldReturnHttpStatusCreated_whenAccountIsCreated() throws Exception {

        when(this.createInvestmentAccountUseCase.createInvestmentAccount(any(Account.class)))
                .thenReturn(createMockAccount());

        final var mockCreateAccountRequest = createMockCreateAccountRequest();

        final var content = this.objectMapper.writeValueAsString(mockCreateAccountRequest);

        final var requestBuilder = post(CREATE_ACCOUNT_ENDPOINT)
                .content(content)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(ACCOUNT_ID))
                .andExpect(jsonPath("$.cash").value(CASH))
                .andExpect(jsonPath("$.issuers").isArray())
                .andExpect(jsonPath("$.issuers").isEmpty());
    }

    @Test
    void sendOrder_shouldReturnHttpStatusOk_whenOrderIsCreated() throws Exception {

        when(this.sendOrderUseCase.sendOrder(any(Order.class)))
                .thenReturn(createMockProcessedOrder());

        final var mockSendOrderRequest = createMockSendOrderRequest();

        final var content = this.objectMapper.writeValueAsString(mockSendOrderRequest);

        final var requestBuilder = post(SEND_ORDER_ENDPOINT)
                .content(content)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.current_balance.cash").value(CASH))
                .andExpect(jsonPath("$.current_balance.issuers").isArray())
                .andExpect(jsonPath("$.current_balance.issuers").isNotEmpty())
                .andExpect(jsonPath("$.business_errors").isArray())
                .andExpect(jsonPath("$.business_errors").isEmpty());
    }
}