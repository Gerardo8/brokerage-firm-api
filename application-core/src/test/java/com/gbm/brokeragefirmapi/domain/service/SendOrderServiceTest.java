package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepository;
import com.gbm.brokeragefirmapi.port.secondary.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.domain.factory.IssuerTransactionMockFactory.createMockIssuerTransaction;
import static com.gbm.brokeragefirmapi.domain.factory.OrderMockFactory.*;
import static com.gbm.brokeragefirmapi.domain.factory.ProcessedOrderMockFactory.createMockProcessedOrder;
import static com.gbm.brokeragefirmapi.domain.factory.StockMockFactory.createMockStock;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.*;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SendOrderServiceTest {

    private SendOrderService sendOrderService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private IssuerTransactionRepository issuerTransactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SendSellOrderOperation sendSellOrderOperation;

    @Mock
    private SendBuyOrderOperation sendBuyOrderOperation;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.sendOrderService = new SendOrderService(
                this.accountRepository,
                this.stockRepository,
                this.issuerTransactionRepository,
                this.sendBuyOrderOperation,
                this.sendSellOrderOperation
        );
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithAccountIssuers_whenIsSendBuyOrder() {

        final var mockOrder = createMockBuyOrder();
        final var mockStock = createMockStock();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.of(createMockAccount()));

        when(this.issuerTransactionRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        when(this.stockRepository.findByIssuerName(mockOrder.getIssuerName()))
                .thenReturn(Optional.of(mockStock));

        when(this.sendBuyOrderOperation.sendOrder(mockOrder, mockStock, mockOrder.getAccount()))
                .thenReturn(createMockProcessedOrder());

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isPositive();
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors()).isEmpty();

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
        verify(this.issuerTransactionRepository).findById(anyString());
        verify(this.stockRepository).findByIssuerName(mockOrder.getIssuerName());
        verify(this.sendBuyOrderOperation).sendOrder(mockOrder, mockStock, mockOrder.getAccount());
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithAccountIssuers_whenIsSendSellOrder() {

        final var mockOrder = createMockSellOrder();
        final var mockStock = createMockStock();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.of(createMockAccount()));

        when(this.issuerTransactionRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        when(this.stockRepository.findByIssuerName(mockOrder.getIssuerName()))
                .thenReturn(Optional.of(mockStock));

        when(this.sendSellOrderOperation.sendOrder(mockOrder, mockStock, mockOrder.getAccount()))
                .thenReturn(createMockProcessedOrder());

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isPositive();
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors()).isEmpty();

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
        verify(this.issuerTransactionRepository).findById(anyString());
        verify(this.stockRepository).findByIssuerName(mockOrder.getIssuerName());
        verify(this.sendSellOrderOperation).sendOrder(mockOrder, mockStock, mockOrder.getAccount());
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithBusinessErrors_whenAccountDoesNotExists() {

        final var mockOrder = createMockBuyOrder();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.empty());

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isEqualTo(ZERO);
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isEmpty();
        assertThat(processedOrder.getBusinessErrors()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors().get(0)).isEqualTo(INVALID_OPERATION);

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithBusinessErrors_whenThereIsCloseMarket() {

        final var mockOrder = createMockOrderWithInvalidTimestamp();
        final var mockAccount = createMockAccount();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.of(mockAccount));

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isEqualTo(mockAccount.getCash());
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isEmpty();
        assertThat(processedOrder.getBusinessErrors()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors().get(0)).isEqualTo(CLOSE_MARKET);

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithBusinessErrors_whenThereIsDuplicateOperation() {

        final var mockOrder = createMockBuyOrder();
        final var mockAccount = createMockAccount();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.of(mockAccount));

        when(this.issuerTransactionRepository.findById(anyString()))
                .thenReturn(Optional.of(createMockIssuerTransaction(mockOrder)));

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isEqualTo(mockAccount.getCash());
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isEmpty();
        assertThat(processedOrder.getBusinessErrors()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors().get(0)).isEqualTo(DUPLICATE_OPERATION);

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
        verify(this.issuerTransactionRepository).findById(anyString());
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithBusinessErrors_whenStockDoesNoExists() {

        final var mockOrder = createMockBuyOrder();
        final var mockAccount = createMockAccount();

        when(this.accountRepository.findAccountById(mockOrder.getAccount().getId()))
                .thenReturn(Optional.of(mockAccount));

        when(this.issuerTransactionRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        when(this.stockRepository.findByIssuerName(mockOrder.getIssuerName()))
                .thenReturn(Optional.empty());

        final var processedOrder = this.sendOrderService.sendOrder(mockOrder);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isEqualTo(mockAccount.getCash());
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isEmpty();
        assertThat(processedOrder.getBusinessErrors()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors().get(0)).isEqualTo(INVALID_OPERATION);

        verify(this.accountRepository).findAccountById(mockOrder.getAccount().getId());
        verify(this.issuerTransactionRepository).findById(anyString());
        verify(this.stockRepository).findByIssuerName(mockOrder.getIssuerName());
    }
}