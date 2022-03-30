package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendOrderRequest {

    @Positive
    private Long timestamp;

    private OrderOperation operation;

    @JsonProperty("issuer_name")
    private String issuerName;

    @Positive
    @JsonProperty("total_shares")
    private Integer totalShares;

    @Positive
    @JsonProperty("share_price")
    private BigDecimal sharePrice;

}
