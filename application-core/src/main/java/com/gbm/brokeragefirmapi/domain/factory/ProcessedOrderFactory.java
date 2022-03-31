package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.CurrentBalance;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProcessedOrderFactory {

    public static ProcessedOrder createFailedProcessedOrder(final Account account, final BusinessError businessError) {

        return new ProcessedOrder(new CurrentBalance(account.getCash(), new ArrayList<>()), singletonList(businessError));
    }
}
