package com.gbm.brokeragefirmapi.config;

import com.gbm.brokeragefirmapi.domain.service.CreateInvestmentAccountService;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountServicePort;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppPrimaryAdapterConfiguration {

    private final AccountRepositoryPort accountRepositoryPort;

    @Bean
    public CreateInvestmentAccountServicePort createInvestmentAccountServicePort() {

        return new CreateInvestmentAccountService(this.accountRepositoryPort);
    }
}
