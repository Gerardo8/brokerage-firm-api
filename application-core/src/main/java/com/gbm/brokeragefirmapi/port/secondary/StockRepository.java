package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Stock;

import java.util.Optional;

public interface StockRepository {

    Optional<Stock> findByIssuerName(String issuerName);
}
