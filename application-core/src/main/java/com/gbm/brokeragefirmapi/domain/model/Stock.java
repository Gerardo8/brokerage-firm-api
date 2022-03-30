package com.gbm.brokeragefirmapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private Integer id;

    private String issuerName;

    private BigDecimal sharePrice;
}
