package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.SendOrderResponse.CurrentBalanceDto;
import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import lombok.NoArgsConstructor;

import static java.time.Instant.ofEpochMilli;
import static java.time.ZoneId.systemDefault;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SendOrderRestMapper {

    public static Order orderFrom(final SendOrderRequest sendOrderRequest, final Long id) {

        return Order.builder()
                .timestamp(ofEpochMilli(sendOrderRequest.getTimestamp()).atZone(systemDefault()).toLocalDateTime())
                .account(new Account(id))
                .operation(sendOrderRequest.getOperation())
                .issuerName(sendOrderRequest.getIssuerName())
                .sharePrice(sendOrderRequest.getSharePrice())
                .totalShares(sendOrderRequest.getTotalShares())
                .build();
    }

    public static SendOrderResponse sendOrderResponseFrom(final ProcessedOrder processedOrder) {

        final var currentBalance = processedOrder.currentBalance();

        final var issuers = currentBalance.issuers()
                .stream()
                .map(CreateAccountRestMapper::createIssuerDtoFrom)
                .collect(toList());

        return new SendOrderResponse(
                new CurrentBalanceDto(currentBalance.cash(), issuers),
                processedOrder.businessErrors()
        );
    }
}
