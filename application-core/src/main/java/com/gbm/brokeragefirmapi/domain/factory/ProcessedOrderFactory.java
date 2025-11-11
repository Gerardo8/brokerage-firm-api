package com.gbm.brokeragefirmapi.domain.factory;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.BusinessError;
import com.gbm.brokeragefirmapi.domain.model.CurrentBalance;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;

import java.util.ArrayList;

import static java.util.Collections.singletonList;

public class ProcessedOrderFactory {

    public static ProcessedOrder createFailedProcessedOrder(final Account account, final BusinessError businessError) {

        return new ProcessedOrder(new CurrentBalance(account.getCash(), new ArrayList<>()), singletonList(businessError));
    }
}
