package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.brokeragefirmapi.domain.model.OrderOperation;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.ZoneId.systemDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendOrderRequest {

    @Positive
    @JsonProperty("timestamp")
    private Long timestamp = LocalDateTime.now().atZone(systemDefault()).toInstant().toEpochMilli();

    @JsonProperty("operation")
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
