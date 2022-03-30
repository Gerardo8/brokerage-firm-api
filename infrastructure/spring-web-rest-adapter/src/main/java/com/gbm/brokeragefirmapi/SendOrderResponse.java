package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError;
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

    private CurrentBalanceDto currentBalance;

    private List<BusinessError> businessErrors = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentBalanceDto {

        private BigDecimal cash;

        private List<IssuerDto> issuers;

    }

}
