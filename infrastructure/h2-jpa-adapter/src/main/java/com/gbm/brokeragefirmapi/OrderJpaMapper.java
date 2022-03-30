package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Order;
import lombok.NoArgsConstructor;

import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountFrom;
import static com.gbm.brokeragefirmapi.AccountJpaMapper.accountJpaEntityFrom;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
                .operation(Order.OrderOperation.fromValue(orderJpaEntity.getOrderOperation()))
                .timestamp(orderJpaEntity.getOperationDate())
                .build();
    }
}
