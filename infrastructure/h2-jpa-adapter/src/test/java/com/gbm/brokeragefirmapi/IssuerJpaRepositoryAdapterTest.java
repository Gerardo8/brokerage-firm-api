package com.gbm.brokeragefirmapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.factory.AccountJpaMockFactory.ACCOUNT_ID;
import static com.gbm.brokeragefirmapi.factory.IssuerJpaMockFactory.*;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.STOCK_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class IssuerJpaRepositoryAdapterTest {

    private IssuerJpaRepositoryAdapter issuerJpaRepositoryAdapter;

    @Mock
    private IssuerJpaRepository issuerJpaRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.issuerJpaRepositoryAdapter = new IssuerJpaRepositoryAdapter(this.issuerJpaRepository);
    }

    @Test
    void findByAccountIdAndStockId_shouldReturnIssuer_whenThereIsIssuer() {

        when(this.issuerJpaRepository.findByAccountIdAndStockId(ACCOUNT_ID, STOCK_ID))
                .thenReturn(Optional.of(createMockIssuerJpaEntity()));

        final var issuer = this.issuerJpaRepositoryAdapter.findByAccountIdAndStockId(ACCOUNT_ID, STOCK_ID)
                .orElse(null);

        assertThat(issuer).isNotNull();
        assertThat(issuer.getAccount()).isNotNull();
        assertThat(issuer.getStock()).isNotNull();
        assertThat(issuer.getTotalShares()).isNotNull();

        verify(this.issuerJpaRepository).findByAccountIdAndStockId(ACCOUNT_ID, STOCK_ID);
    }

    @Test
    void createIssuer_shouldCreateIssuer_whenIssuerIsSaved() {

        this.issuerJpaRepositoryAdapter.createIssuer(createMockIssuer());

        verify(this.issuerJpaRepository).save(any(IssuerJpaEntity.class));
    }

    @Test
    void findAllByAccountId_shouldReturnIssuer_whenThereIsIssuer() {

        when(this.issuerJpaRepository.findAllByAccountId(ACCOUNT_ID))
                .thenReturn(createMockIssuerJpaEntityList());

        final var issuers = this.issuerJpaRepositoryAdapter.findAllByAccountId(ACCOUNT_ID);

        assertThat(issuers).isNotEmpty();

        verify(this.issuerJpaRepository).findAllByAccountId(ACCOUNT_ID);
    }
}