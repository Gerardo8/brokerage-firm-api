package com.gbm.brokeragefirmapi.factory;

import com.gbm.brokeragefirmapi.IssuerTransactionRedisEntity
import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction
import com.gbm.brokeragefirmapi.domain.model.OrderOperation.BUY
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit.MINUTES

class IssuerTransactionRedisMockFactory {

    companion object {

        const val ISSUER_TRANSACTION_ID = "BUY::AAPL::2"
        private const val ISSUER_NAME = "APPL"
        private const val TOTAL_SHARES = 2
        private const val TIME_TO_LIVE = 5L
        private val SHARE_PRICE = BigDecimal.valueOf(80L)
        private val TIMESTAMP = LocalDateTime.now()

        fun createIssuerTransaction() = IssuerTransaction.builder()
                .id(ISSUER_TRANSACTION_ID)
                .issuerName(ISSUER_NAME)
                .totalShares(TOTAL_SHARES)
                .sharePrice(SHARE_PRICE)
                .timestamp(TIMESTAMP)
                .operation(BUY)
                .timeToLive(MINUTES.toMillis(TIME_TO_LIVE))
                .build()


        fun createIssuerTransactionRedisEntity() = IssuerTransactionRedisEntity(
                ISSUER_TRANSACTION_ID,
                TIMESTAMP,
                BUY,
                ISSUER_NAME,
                TOTAL_SHARES,
                SHARE_PRICE,
                MINUTES.toMillis(TIME_TO_LIVE)
        )
    }

}
