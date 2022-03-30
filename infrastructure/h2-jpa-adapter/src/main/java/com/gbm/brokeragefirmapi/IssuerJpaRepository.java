package com.gbm.brokeragefirmapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssuerJpaRepository extends JpaRepository<IssuerJpaEntity, Integer> {

    Optional<IssuerJpaEntity> findByAccountIdAndStockId(Long accountId, Integer stockId);

    List<IssuerJpaEntity> findAllByAccountId(Long accountId);
}
