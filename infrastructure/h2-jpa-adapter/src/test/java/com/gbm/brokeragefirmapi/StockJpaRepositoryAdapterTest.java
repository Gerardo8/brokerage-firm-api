package com.gbm.brokeragefirmapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.ISSUER_NAME;
import static com.gbm.brokeragefirmapi.factory.StockJpaMockFactory.createMockStockJpaEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class StockJpaRepositoryAdapterTest {

    private StockJpaRepositoryAdapter stockJpaRepositoryAdapter;

    @Mock
    private StockJpaRepository stockJpaRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.stockJpaRepositoryAdapter = new StockJpaRepositoryAdapter(this.stockJpaRepository);
    }

    @Test
    void findByIssuerName_shouldReturnIssuer_whenThereIsIssuer() {

        when(this.stockJpaRepository.findByIssuerName(ISSUER_NAME))
                .thenReturn(Optional.of(createMockStockJpaEntity()));

        final var issuer = this.stockJpaRepositoryAdapter.findByIssuerName(ISSUER_NAME);

        assertThat(issuer).isNotEmpty();

        verify(this.stockJpaRepository).findByIssuerName(ISSUER_NAME);
    }
}