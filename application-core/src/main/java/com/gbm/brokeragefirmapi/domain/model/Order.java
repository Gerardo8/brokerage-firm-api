package com.gbm.brokeragefirmapi.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private LocalDateTime timestamp;

    private OrderOperation operation;

    private String issuerName;

    private Integer totalShares;

    private BigDecimal sharePrice;

    private Account account;

    @RequiredArgsConstructor
    public enum OrderOperation {

        SELL("SELL"),

        BUY("BUY");

        private final String value;

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
}
