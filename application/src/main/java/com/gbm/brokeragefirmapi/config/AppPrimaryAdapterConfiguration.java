package com.gbm.brokeragefirmapi.config;

import com.gbm.brokeragefirmapi.domain.service.CreateInvestmentAccountService;
import com.gbm.brokeragefirmapi.domain.service.SendBuyOrderOperation;
import com.gbm.brokeragefirmapi.domain.service.SendOrderService;
import com.gbm.brokeragefirmapi.domain.service.SendSellOrderOperation;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountServicePort;
import com.gbm.brokeragefirmapi.port.primary.SendOrderServicePort;
import com.gbm.brokeragefirmapi.port.secondary.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppPrimaryAdapterConfiguration {

    private final AccountRepositoryPort accountRepositoryPort;
    private final StockRepositoryPort stockRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;
    private final IssuerRepositoryPort issuerRepositoryPort;
    private final IssuerTransactionRepositoryPort issuerTransactionRepositoryPort;

    @Bean
    public CreateInvestmentAccountServicePort createInvestmentAccountServicePort() {

        return new CreateInvestmentAccountService(this.accountRepositoryPort);
    }

    @Bean
    public SendOrderServicePort sendOrderServicePort() {

        return new SendOrderService(
                this.accountRepositoryPort,
                this.stockRepositoryPort,
                this.issuerTransactionRepositoryPort,
                new SendBuyOrderOperation(
                        this.orderRepositoryPort,
                        this.issuerRepositoryPort,
                        this.accountRepositoryPort,
                        this.issuerTransactionRepositoryPort
                ),
                new SendSellOrderOperation(
                        this.orderRepositoryPort,
                        this.issuerRepositoryPort,
                        this.accountRepositoryPort,
                        this.issuerTransactionRepositoryPort
                )
        );
    }
}
