package com.gbm.brokeragefirmapi.domain.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Stock(
        Integer id,

         String issuerName,

         BigDecimal sharePrice
) {
}
