package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.port.secondary.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendSellOrderService {

    private final OrderRepositoryPort orderRepositoryPort;

    public ProcessedOrder sendOrder() {

        return null;
    }
}
