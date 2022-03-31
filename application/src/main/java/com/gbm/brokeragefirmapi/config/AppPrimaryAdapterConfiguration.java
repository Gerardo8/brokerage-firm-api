package com.gbm.brokeragefirmapi.config;

import com.gbm.brokeragefirmapi.domain.service.CreateInvestmentAccountService;
import com.gbm.brokeragefirmapi.domain.service.SendBuyOrderOperation;
import com.gbm.brokeragefirmapi.domain.service.SendOrderService;
import com.gbm.brokeragefirmapi.domain.service.SendSellOrderOperation;
import com.gbm.brokeragefirmapi.port.primary.CreateInvestmentAccountUseCase;
import com.gbm.brokeragefirmapi.port.primary.SendOrderUseCase;
import com.gbm.brokeragefirmapi.port.secondary.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppPrimaryAdapterConfiguration {

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final IssuerRepository issuerRepository;
    private final IssuerTransactionRepository issuerTransactionRepository;

    @Bean
    public CreateInvestmentAccountUseCase createInvestmentAccountServicePort() {

        return new CreateInvestmentAccountService(this.accountRepository);
    }

    @Bean
    public SendOrderUseCase sendOrderServicePort() {

        return new SendOrderService(
                this.accountRepository,
                this.stockRepository,
                this.issuerTransactionRepository,
                new SendBuyOrderOperation(
                        this.orderRepository,
                        this.issuerRepository,
                        this.accountRepository,
                        this.issuerTransactionRepository
                ),
                new SendSellOrderOperation(
                        this.orderRepository,
                        this.issuerRepository,
                        this.accountRepository,
                        this.issuerTransactionRepository
                )
        );
    }
}
