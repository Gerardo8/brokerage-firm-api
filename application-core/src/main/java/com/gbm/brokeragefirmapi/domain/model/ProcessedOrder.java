package com.gbm.brokeragefirmapi.domain.model;

import java.util.List;


public record ProcessedOrder(
        CurrentBalance currentBalance,

        List<BusinessError> businessErrors
) {
}
