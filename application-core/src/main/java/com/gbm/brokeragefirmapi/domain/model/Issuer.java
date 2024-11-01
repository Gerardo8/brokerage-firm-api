package com.gbm.brokeragefirmapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Issuer {

    private Integer id;

    private Integer totalShares;

    private Account account;

    private Stock stock;

}
