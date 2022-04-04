package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.factory.AccountRestControllerMockFactory.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BrokerageFirmApiApplicationIntegrationTest {

    private static final String CREATE_ACCOUNT_ENDPOINT = "/api/v1/accounts";
    private static final String SEND_ORDER_ENDPOINT = "/api/v1/accounts/1/orders";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IssuerTransactionRedisRepository issuerTransactionRedisRepository;

    @BeforeEach
    public void beforeAll() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    void createInvestmentAccount_shouldReturnHttpStatusCreated_whenAccountIsCreated() throws Exception {

        final var mockCreateAccountRequest = createMockCreateAccountRequest();

        final var content = this.objectMapper.writeValueAsString(mockCreateAccountRequest);

        final var requestBuilder = post(CREATE_ACCOUNT_ENDPOINT)
                .content(content)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.cash").value(CASH))
                .andExpect(jsonPath("$.issuers").isArray())
                .andExpect(jsonPath("$.issuers").isEmpty());
    }

    @Test
    void sendOrder_shouldReturnHttpStatusOk_whenOrderIsCreated() throws Exception {

        final var mockSendOrderRequest = createMockSendOrderRequest();

        when(this.issuerTransactionRedisRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        final var content = this.objectMapper.writeValueAsString(mockSendOrderRequest);

        final var requestBuilder = post(SEND_ORDER_ENDPOINT)
                .content(content)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.current_balance.cash").isNumber())
                .andExpect(jsonPath("$.current_balance.issuers").isArray())
                .andExpect(jsonPath("$.current_balance.issuers").isNotEmpty())
                .andExpect(jsonPath("$.business_errors").isArray())
                .andExpect(jsonPath("$.business_errors").isEmpty());
    }
}