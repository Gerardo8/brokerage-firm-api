package com.gbm.brokeragefirmapi.port.primary;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;

public interface SendOrderUseCase {

    ProcessedOrder sendOrder(Order order);
}
