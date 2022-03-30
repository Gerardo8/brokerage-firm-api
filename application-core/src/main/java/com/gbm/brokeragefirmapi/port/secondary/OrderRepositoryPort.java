package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Order;

public interface OrderRepositoryPort {

    void createOrder(Order order);

}
