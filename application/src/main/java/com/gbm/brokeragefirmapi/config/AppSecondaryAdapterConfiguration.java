package com.gbm.brokeragefirmapi.config;

import com.gbm.brokeragefirmapi.*;
import com.gbm.brokeragefirmapi.port.secondary.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppSecondaryAdapterConfiguration {

    private final AccountJpaRepository accountJpaRepository;
    private final IssuerJpaRepository issuerJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final StockJpaRepository stockJpaRepository;
    private final IssuerTransactionRedisRepository issuerTransactionRedisRepository;

    @Bean
    public AccountRepositoryPort accountRepositoryPort() {

        return new AccountJpaRepositoryAdapter(this.accountJpaRepository);
    }

    @Bean
    public IssuerRepositoryPort issuerRepositoryPort() {

        return new IssuerJpaRepositoryAdapter(this.issuerJpaRepository);
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort() {

        return new OrderJpaRepositoryAdapter(this.orderJpaRepository);
    }

    @Bean
    public StockRepositoryPort stockRepositoryPort() {

        return new StockJpaRepositoryAdapter(this.stockJpaRepository);
    }

    @Bean
    public IssuerTransactionRepositoryPort issuerTransactionRepositoryPort() {

        return new IssuerTransactionRedisRepositoryAdapter(this.issuerTransactionRedisRepository);
    }
}
