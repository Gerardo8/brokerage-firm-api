package com.gbm.brokeragefirmapi.config;

import com.gbm.brokeragefirmapi.AccountJpaRepository;
import com.gbm.brokeragefirmapi.AccountJpaRepositoryAdapter;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppSecondaryAdapterConfiguration {

    private final AccountJpaRepository accountJpaRepository;

    @Bean
    public AccountRepositoryPort accountRepositoryPort() {

        return new AccountJpaRepositoryAdapter(this.accountJpaRepository);
    }
}
