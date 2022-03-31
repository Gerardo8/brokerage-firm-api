package com.gbm.brokeragefirmapi.domain.service;

import com.gbm.brokeragefirmapi.domain.model.Account;
import com.gbm.brokeragefirmapi.domain.model.Order;
import com.gbm.brokeragefirmapi.domain.model.ProcessedOrder;
import com.gbm.brokeragefirmapi.port.primary.SendOrderUseCase;
import com.gbm.brokeragefirmapi.port.secondary.AccountRepository;
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepository;
import com.gbm.brokeragefirmapi.port.secondary.StockRepository;
import lombok.RequiredArgsConstructor;

import static com.gbm.brokeragefirmapi.domain.factory.IssuerTransactionFactory.createIssuerTransactionId;
import static com.gbm.brokeragefirmapi.domain.factory.ProcessedOrderFactory.createFailedProcessedOrder;
import static com.gbm.brokeragefirmapi.domain.model.ProcessedOrder.BusinessError.*;
import static com.gbm.brokeragefirmapi.utils.SendOrderConstants.SIX_AM;
import static com.gbm.brokeragefirmapi.utils.SendOrderConstants.THREE_PM;

@RequiredArgsConstructor
public class SendOrderService implements SendOrderUseCase {

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final IssuerTransactionRepository issuerTransactionRepository;
    private final SendBuyOrderOperation sendBuyOrderOperation;
    private final SendSellOrderOperation sendSellOrderOperation;

    @Override
    public ProcessedOrder sendOrder(final Order order) {

        final var time = order.getTimestamp().toLocalTime();

        final var optionalAccount = this.accountRepository
                .findAccountById(order.getAccount().getId());

        if (optionalAccount.isEmpty()) {

            return createFailedProcessedOrder(new Account(), INVALID_OPERATION);
        }

        final var account = optionalAccount.get();

        if (!time.isAfter(SIX_AM) || !time.isBefore(THREE_PM)) {

            return createFailedProcessedOrder(account, CLOSE_MARKET);
        }

        final var optionalIssuerTransaction = this.issuerTransactionRepository.findById(createIssuerTransactionId(order));

        if (optionalIssuerTransaction.isPresent()) {

            return createFailedProcessedOrder(account, DUPLICATE_OPERATION);
        }

        final var optionalStock = this.stockRepository
                .findByIssuerName(order.getIssuerName());

        if (optionalStock.isEmpty()) {

            return createFailedProcessedOrder(account, INVALID_OPERATION);
        }

        final var stock = optionalStock.get();

        order.setIssuerName(stock.getIssuerName());
        order.setSharePrice(stock.getSharePrice());

        return switch (order.getOperation()) {
            case BUY -> this.sendBuyOrderOperation.sendOrder(order, stock, account);
            case SELL -> this.sendSellOrderOperation.sendOrder(order, stock, account);
        };

    }

}
