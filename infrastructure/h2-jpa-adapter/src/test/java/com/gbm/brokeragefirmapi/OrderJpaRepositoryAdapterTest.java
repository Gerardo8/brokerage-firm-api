package com.gbm.brokeragefirmapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.gbm.brokeragefirmapi.factory.OrderJpaRepositoryMockFactory.createMockOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderJpaRepositoryAdapterTest {

    private OrderJpaRepositoryAdapter orderJpaRepositoryAdapter;

    @Mock
    private OrderJpaRepository orderJpaRepository;

    @BeforeEach
    void setUp() {

        openMocks(this);

        this.orderJpaRepositoryAdapter = new OrderJpaRepositoryAdapter(this.orderJpaRepository);
    }

    @Test
    void createOrder_shouldCreateOrder_whenOrderIsSaved() {

        this.orderJpaRepositoryAdapter.createOrder(createMockOrder());

        verify(this.orderJpaRepository).save(any(OrderJpaEntity.class));
    }
}