package com.gbm.brokeragefirmapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssuerTransaction {

    private String id;

    private LocalDateTime timestamp;

    private Order.OrderOperation operation;

    private String issuerName;

    private Integer totalShares;

    private BigDecimal sharePrice;

    private Long timeToLive;
}
