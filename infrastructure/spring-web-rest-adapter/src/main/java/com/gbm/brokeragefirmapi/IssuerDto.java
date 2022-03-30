package com.gbm.brokeragefirmapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssuerDto {

    private String issuerName;

    private Integer totalShares;

    private BigDecimal sharePrice;

}
