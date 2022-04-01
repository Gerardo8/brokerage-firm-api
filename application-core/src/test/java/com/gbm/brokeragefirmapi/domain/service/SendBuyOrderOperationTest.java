package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepository;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccount;
import static com.gbm.brokeragefirmapi.domain.factory.AccountMockFactory.createMockAccountWithoutEnoughCash;
import static com.gbm.brokeragefirmapi.domain.factory.IssuerMockFactory.createMockIssuer;
import static com.gbm.brokeragefirmapi.domain.factory.IssuerMockFactory.createMockIssuerWithoutAvailableStocksList;
import static com.gbm.brokeragefirmapi.domain.factory.OrderMockFactory.createMockBuyOrder;
import static com.gbm.brokeragefirmapi.domain.factory.StockMockFactory.createMockStock;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.INSUFFICIENT_BALANCE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SendBuyOrderOperationTest {

    private SendBuyOrderOperation sendBuyOrderOperation;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private IssuerRepository issuerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IssuerTransactionRepository issuerTransactionRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.sendBuyOrderOperation = new SendBuyOrderOperation(
                this.orderRepository,
                this.issuerRepository,
                this.accountRepository,
                this.issuerTransactionRepository
        );
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithAccountIssuers_whenSuccessFlow() {

        final var mockAccount = createMockAccount();
        final var mockStock = createMockStock();
        final var mockOrder = createMockBuyOrder();

        when(this.issuerRepository.findByAccountIdAndStockId(mockAccount.getId(), mockStock.getId()))
                .thenReturn(Optional.of(createMockIssuer()));

        when(this.issuerRepository.findAllByAccountId(mockAccount.getId()))
                .thenReturn(createMockIssuerWithoutAvailableStocksList());

        final var processedOrder = this.sendBuyOrderOperation
                .sendOrder(mockOrder, mockStock, mockAccount);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isPositive();
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors()).isEmpty();

        verify(this.issuerRepository).findAllByAccountId(mockAccount.getId());
        verify(this.issuerRepository).findByAccountIdAndStockId(mockAccount.getId(), mockStock.getId());
        verify(this.issuerRepository).createIssuer(any(Issuer.class));
        verify(this.orderRepository).createOrder(any(Order.class));
        verify(this.accountRepository).createAccount(any(Account.class));
        verify(this.issuerTransactionRepository).createIssuerTransaction(any(IssuerTransaction.class));
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithAccountIssuers_whenIssuerDoesNotExists() {

        final var mockAccount = createMockAccount();
        final var mockStock = createMockStock();
        final var mockOrder = createMockBuyOrder();

        when(this.issuerRepository.findByAccountIdAndStockId(mockAccount.getId(), mockStock.getId()))
                .thenReturn(Optional.empty());

        when(this.issuerRepository.findAllByAccountId(mockAccount.getId()))
                .thenReturn(createMockIssuerWithoutAvailableStocksList());

        final var processedOrder = this.sendBuyOrderOperation
                .sendOrder(mockOrder, mockStock, mockAccount);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isPositive();
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors()).isEmpty();

        verify(this.issuerRepository).findAllByAccountId(mockAccount.getId());
        verify(this.issuerRepository).findByAccountIdAndStockId(mockAccount.getId(), mockStock.getId());
        verify(this.issuerRepository).createIssuer(any(Issuer.class));
        verify(this.orderRepository).createOrder(any(Order.class));
        verify(this.accountRepository).createAccount(any(Account.class));
        verify(this.issuerTransactionRepository).createIssuerTransaction(any(IssuerTransaction.class));
    }

    @Test
    void sendOrder_shouldReturnProcessedOrderWithBusinessErrors_whenThereIsNotEnoughCash() {

        final var mockAccount = createMockAccountWithoutEnoughCash();
        final var mockStock = createMockStock();
        final var mockOrder = createMockBuyOrder();


        final var processedOrder = this.sendBuyOrderOperation
                .sendOrder(mockOrder, mockStock, mockAccount);

        assertThat(processedOrder).isNotNull();
        assertThat(processedOrder.getCurrentBalance().getCash()).isEqualTo(mockAccount.getCash());
        assertThat(processedOrder.getCurrentBalance().getIssuers()).isEmpty();
        assertThat(processedOrder.getBusinessErrors()).isNotEmpty();
        assertThat(processedOrder.getBusinessErrors().get(0)).isEqualTo(INSUFFICIENT_BALANCE);

    }
}