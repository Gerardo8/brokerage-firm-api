package com.gbm.brokeragefirmapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cash")
    private BigDecimal cash;

    @JsonProperty("issuers")
    private List<IssuerDto> issuers;
}
