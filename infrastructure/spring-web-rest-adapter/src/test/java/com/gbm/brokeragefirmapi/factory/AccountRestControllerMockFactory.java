package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.CreateAccountRequest;
import com.gbm.brokeragefirmapi.SendOrderRequest;
import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.domain.model.CurrentBalance;
import com.gbm.brokeragefirmapi.domain.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.gbm.brokeragefirmapi.domain.model.OrderOperation.BUY;
import static java.time.LocalTime.NOON;
import static java.time.ZoneId.systemDefault;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class AccountRestControllerMockFactory {

    public static final BigDecimal CASH = BigDecimal.valueOf(1_000);
    public static final Long ACCOUNT_ID = 1L;
    public static final String ISSUER_NAME = "AAPL";
    public static final BigDecimal SHARE_PRICE = BigDecimal.valueOf(80L);
    public static final Integer TOTAL_SHARES = 2;
    public static final Integer ISSUER_ID = 1;
    public static final Integer STOCK_ID = 1;
    public static final Long TIMESTAMP = LocalDateTime.of(LocalDate.now(), NOON).atZone(systemDefault()).toInstant().toEpochMilli();

    public static CreateAccountRequest createMockCreateAccountRequest() {

        return new CreateAccountRequest(CASH);
    }

    public static Account createMockAccount() {

        return Account
                .builder()
                .id(ACCOUNT_ID)
                .cash(CASH)
                .issuers(emptyList())
                .build();
    }

    public static Stock createMockStock() {

        return Stock.builder()
                .id(STOCK_ID)
                .issuerName(ISSUER_NAME)
                .sharePrice(SHARE_PRICE)
                .build();
    }

    public static Issuer createMockIssuer() {

        return Issuer.builder()
                .id(ISSUER_ID)
                .account(createMockAccount())
                .stock(createMockStock())
                .totalShares(TOTAL_SHARES)
                .build();
    }

    public static ProcessedOrder createMockProcessedOrder() {

        return new ProcessedOrder(new CurrentBalance(CASH, singletonList(createMockIssuer())), emptyList());
    }

    public static SendOrderRequest createMockSendOrderRequest() {

        return new SendOrderRequest(
                TIMESTAMP,
                BUY,
                ISSUER_NAME,
                TOTAL_SHARES,
                SHARE_PRICE
        );
    }
}
