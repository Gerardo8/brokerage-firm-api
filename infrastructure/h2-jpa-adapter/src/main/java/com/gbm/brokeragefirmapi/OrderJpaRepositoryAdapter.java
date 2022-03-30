package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.gbm.brokeragefirmapi.OrderJpaMapper.orderJpaEntityFrom;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderJpaRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void createOrder(final Order order) {

        final OrderJpaEntity orderJpaEntity = orderJpaEntityFrom(order);
        this.orderJpaRepository.save(orderJpaEntity);

    }

}
