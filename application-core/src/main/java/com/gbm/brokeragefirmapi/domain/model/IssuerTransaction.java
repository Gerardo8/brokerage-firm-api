package com.gbm.brokeragefirmapi.domain.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record IssuerTransaction(
        String id,

        String issuerName,

        Integer totalShares,

        BigDecimal sharePrice,

        LocalDateTime timestamp,

        OrderOperation operation,

        Long timeToLive
) {
}
