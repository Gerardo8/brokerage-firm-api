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
    public AccountRepository accountRepository() {

        return new AccountJpaRepositoryAdapter(this.accountJpaRepository);
    }

    @Bean
    public IssuerRepository issuerRepository() {

        return new IssuerJpaRepositoryAdapter(this.issuerJpaRepository);
    }

    @Bean
    public OrderRepository orderRepository() {

        return new OrderJpaRepositoryAdapter(this.orderJpaRepository);
    }

    @Bean
    public StockRepository stockRepository() {

        return new StockJpaRepositoryAdapter(this.stockJpaRepository);
    }

    @Bean
    public IssuerTransactionRepository issuerTransactionRepository() {

        return new IssuerTransactionRedisRepositoryAdapter(this.issuerTransactionRedisRepository);
    }
}
