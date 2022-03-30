package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.brokeragefirmapi.domain.model.Order.OrderOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendOrderRequest {

    private Long timestamp;

    private OrderOperation operation;

    @JsonProperty("issuer_name")
    private String issuerName;

    @JsonProperty("total_shares")
    private Integer totalShares;

    @JsonProperty("share_price")
    private BigDecimal sharePrice;

}
