package com.gbm.brokeragefirmapi.domain.model;

import java.math.BigDecimal;
import java.util.List;

public record CurrentBalance(

        BigDecimal cash,

        List<Issuer> issuers
) {
}
