package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.OrderOperation;

import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountFrom;
import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountJpaEntityFrom;

public class OrderJpaMapper {

    public static OrderJpaEntity orderJpaEntityFrom(final Order order) {

        return OrderJpaEntity.builder()
                .account(accountJpaEntityFrom(order.getAccount()))
                .issuerName(order.getIssuerName())
                .sharePrice(order.getSharePrice())
                .totalShares(order.getTotalShares())
                .orderOperation(order.getOperation().getValue())
                .operationDate(order.getTimestamp())
                .build();
    }

    public static Order orderFrom(final OrderJpaEntity orderJpaEntity) {

        return Order.builder()
                .account(accountFrom(orderJpaEntity.getAccount()))
                .totalShares(orderJpaEntity.getTotalShares())
                .sharePrice(orderJpaEntity.getSharePrice())
                .issuerName(orderJpaEntity.getIssuerName())
                .operation(OrderOperation.fromValue(orderJpaEntity.getOrderOperation()))
                .timestamp(orderJpaEntity.getOperationDate())
                .build();
    }
}
