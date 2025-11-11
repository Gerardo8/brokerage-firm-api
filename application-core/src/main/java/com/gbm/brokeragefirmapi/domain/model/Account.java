package com.gbm.brokeragefirmapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public final class Account {

    private Long id;
    private BigDecimal cash;
    private List<Issuer> issuers;

    public Account(Long id, BigDecimal cash, List<Issuer> issuers) {
        this.id = id;
        this.cash = cash;
        this.issuers = issuers;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(BigDecimal cash) {
        this.cash = cash;
    }

    public Account(BigDecimal cash, List<Issuer> issuers) {
        this.cash = cash;
        this.issuers = issuers;
    }
}
