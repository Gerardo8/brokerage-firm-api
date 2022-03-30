package com.gbm.brokeragefirmapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockJpaRepository extends JpaRepository<StockJpaEntity, Integer> {

    Optional<StockJpaEntity> findByIssuerName(String issuerName);
}
