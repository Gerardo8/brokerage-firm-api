package com.gbm.brokeragefirmapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class Issuer {

    private Integer id;
    private Integer totalShares;
    private Account account;
    private Stock stock;

    public Issuer(Integer id, Integer totalShares, Account account, Stock stock) {
        this.id = id;
        this.totalShares = totalShares;
        this.account = account;
        this.stock = stock;
    }

    public Issuer(Integer totalShares, Account account, Stock stock) {
        this.totalShares = totalShares;
        this.account = account;
        this.stock = stock;
    }
}
