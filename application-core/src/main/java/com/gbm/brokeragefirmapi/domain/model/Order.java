package com.gbm.brokeragefirmapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Order {
    private LocalDateTime timestamp;

    private OrderOperation operation;

    private String issuerName;

    private Integer totalShares;

    private BigDecimal sharePrice;

    private Account account;

}
