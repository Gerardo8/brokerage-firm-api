package com.gbm.brokeragefirmapi.domain.model;

public enum OrderOperation {

    SELL("SELL"),

    BUY("BUY");

    private final String value;

    OrderOperation(String value) {
        this.value = value;
    }

    public String getValue() {

        return this.value;
    }

    public static OrderOperation fromValue(final String value) {

        for (OrderOperation orderOperation : OrderOperation.values()) {
            if (orderOperation.value.equals(value)) {

                return orderOperation;
            }
        }

        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
