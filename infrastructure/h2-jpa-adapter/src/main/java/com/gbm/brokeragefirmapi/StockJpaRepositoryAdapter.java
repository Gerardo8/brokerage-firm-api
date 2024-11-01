package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Stock;
import com.gbm.brokeragefirmapi.port.secondary.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class StockJpaRepositoryAdapter implements StockRepository {

    private final StockJpaRepository stockJpaRepository;

    @Override
    public Optional<Stock> findByIssuerName(final String issuerName) {

        return this.stockJpaRepository.findByIssuerName(issuerName)
                .map(StockJpaMapper::stockFrom);
    }
}
