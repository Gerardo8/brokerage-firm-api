package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Stock;
import com.gbm.brokeragefirmapi.port.secondary.StockRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class StockJpaRepositoryAdapter implements StockRepositoryPort {

    private final StockJpaRepository stockJpaRepository;

    @Override
    public Optional<Stock> findByIssuerName(final String issuerName) {

        return this.stockJpaRepository.findByIssuerName(issuerName)
                .map(StockJpaMapper::stockFrom);
    }
}
