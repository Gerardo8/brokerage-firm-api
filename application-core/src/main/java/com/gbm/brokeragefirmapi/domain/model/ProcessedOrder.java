package com.gbm.brokeragefirmapi.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedOrder {

    private CurrentBalance currentBalance;

    private List<BusinessError> businessErrors = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentBalance {

        private BigDecimal cash;

        private List<Issuer> issuers;

    }

    @RequiredArgsConstructor
    public enum BusinessError {

        INSUFFICIENT_BALANCE("INSUFFICIENT_BALANCE"),
        INSUFFICIENT_STOCKS("INSUFFICIENT_STOCKS"),
        DUPLICATE_OPERATION("DUPLICATE_OPERATION"),
        CLOSE_MARKET("CLOSE_MARKET"),
        INVALID_OPERATION("INVALID_OPERATION");

        private final String value;

        public String getValue() {

            return this.value;
        }
    }
}
