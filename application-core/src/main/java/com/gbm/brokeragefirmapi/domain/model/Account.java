package com.gbm.brokeragefirmapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    private BigDecimal cash;

    private List<Issuer> issuers;

    public Account(final Long id) {
        this.id = id;
    }

    public Account(final BigDecimal cash) {
        this.cash = cash;
    }
}
