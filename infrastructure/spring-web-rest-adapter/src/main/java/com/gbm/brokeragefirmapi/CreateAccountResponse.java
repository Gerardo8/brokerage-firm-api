package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Issuer;
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

    private Long id;

    private BigDecimal cash;

    private List<Issuer> issuers;
}
