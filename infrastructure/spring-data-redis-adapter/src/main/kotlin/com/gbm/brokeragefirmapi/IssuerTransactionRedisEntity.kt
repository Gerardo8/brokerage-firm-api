package com.gbm.brokeragefirmapi

import com.gbm.brokeragefirmapi.domain.model.OrderOperation
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.MINUTES

@RedisHash("issuer-transaction")
data class IssuerTransactionRedisEntity(

        @Id
        val id: String,

        val timestamp: LocalDateTime = LocalDateTime.now(),

        val operation: OrderOperation? = null,

        val issuerName: String = "",

        val totalShares: Int = 0,

        val sharePrice: BigDecimal = ZERO,

        @TimeToLive(unit = MILLISECONDS)
        val timeToLive: Long = MINUTES.toMillis(5L)

)
