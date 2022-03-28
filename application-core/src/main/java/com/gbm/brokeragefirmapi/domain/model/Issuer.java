package com.gbm.brokeragefirmapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issuer {

    private String issuerName;

    private Integer totalShares;

    private BigDecimal sharePrice;

}
