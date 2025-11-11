package com.gbm.brokeragefirmapi.domain.model;

public enum BusinessError {

    INSUFFICIENT_BALANCE("INSUFFICIENT_BALANCE"),
    INSUFFICIENT_STOCKS("INSUFFICIENT_STOCKS"),
    DUPLICATE_OPERATION("DUPLICATE_OPERATION"),
    CLOSE_MARKET("CLOSE_MARKET"),
    INVALID_OPERATION("INVALID_OPERATION");

    private final String value;

    BusinessError(String value) {
        this.value = value;
    }

    public String getValue() {

        return this.value;
    }
}
