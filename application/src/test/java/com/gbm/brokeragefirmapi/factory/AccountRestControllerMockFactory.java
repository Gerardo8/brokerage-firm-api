package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.CreateAccountRequest;
import com.gbm.brokeragefirmapi.SendOrderRequest;
import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.domain.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.gbm.brokeragefirmapi.domain.model.OrderOperation.BUY;
import static java.time.LocalTime.NOON;
import static java.time.ZoneId.systemDefault;
import static java.util.Collections.emptyList;

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

        return new Account(
                ACCOUNT_ID,
                CASH,
                emptyList()
        );
    }

    public static Stock createMockStock() {

        return new Stock(
                STOCK_ID,
                ISSUER_NAME,
                SHARE_PRICE
        );
    }

    public static Issuer createMockIssuer() {

        return new Issuer(
                ISSUER_ID,
                TOTAL_SHARES,
                createMockAccount(),
                createMockStock()
        );
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
