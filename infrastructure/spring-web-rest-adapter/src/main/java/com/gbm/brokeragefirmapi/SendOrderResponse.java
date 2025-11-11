package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.brokeragefirmapi.domain.model.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendOrderResponse {

    @JsonProperty("current_balance")
    private CurrentBalanceDto currentBalance;

    @JsonProperty("business_errors")
    private List<BusinessError> businessErrors = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentBalanceDto {

        @JsonProperty("cash")
        private BigDecimal cash;

        @JsonProperty("issuers")
        private List<IssuerDto> issuers;

    }

}
