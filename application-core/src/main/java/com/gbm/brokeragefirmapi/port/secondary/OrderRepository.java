package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.Order;

public interface OrderRepository {

    void createOrder(Order order);

}
