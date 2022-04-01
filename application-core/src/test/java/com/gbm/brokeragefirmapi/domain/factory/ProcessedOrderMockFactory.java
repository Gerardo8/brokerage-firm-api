package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.CurrentBalance;

import java.math.BigDecimal;

import static com.gbm.brokeragefirmapi.domain.factory.IssuerMockFactory.createMockIssuerList;
import static java.util.Collections.emptyList;

public class ProcessedOrderMockFactory {

    public static final BigDecimal CASH = BigDecimal.valueOf(1_000);

    public static ProcessedOrder createMockProcessedOrder() {

        return new ProcessedOrder(new CurrentBalance(CASH, createMockIssuerList()), emptyList());
    }
}
