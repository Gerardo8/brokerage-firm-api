package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Issuer;

import java.util.List;
import java.util.Optional;

public interface IssuerRepositoryPort {

    Optional<Issuer> findByAccountIdAndStockId(Long accountId, Integer stockId);

    void createIssuer(Issuer issuer);

    List<Issuer> findAllByAccountId(Long accountId);
}
