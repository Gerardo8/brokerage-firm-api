package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.gbm.brokeragefirmapi.OrderJpaMapper.orderJpaEntityFrom;

@Transactional
@RequiredArgsConstructor
public class OrderJpaRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void createOrder(final Order order) {

        final OrderJpaEntity orderJpaEntity = orderJpaEntityFrom(order);
        this.orderJpaRepository.save(orderJpaEntity);

    }

}
