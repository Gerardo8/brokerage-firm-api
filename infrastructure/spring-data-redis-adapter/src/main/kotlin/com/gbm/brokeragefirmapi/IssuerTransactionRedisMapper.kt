package com.gbm.brokeragefirmapi

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction

class IssuerTransactionRedisMapper {

    companion object {

        @JvmStatic
        fun issuerTransactionFrom(issuerTransactionRedisEntity: IssuerTransactionRedisEntity) = IssuerTransaction.builder()
                .id(issuerTransactionRedisEntity.id)
                .issuerName(issuerTransactionRedisEntity.issuerName)
                .operation(issuerTransactionRedisEntity.operation)
                .sharePrice(issuerTransactionRedisEntity.sharePrice)
                .timeToLive(issuerTransactionRedisEntity.timeToLive)
                .timestamp(issuerTransactionRedisEntity.timestamp)
                .totalShares(issuerTransactionRedisEntity.totalShares)
                .build()!!

        @JvmStatic
        fun issuerTransactionRedisEntityFrom(issuerTransaction: IssuerTransaction) = IssuerTransactionRedisEntity(
                issuerTransaction.id,
                issuerTransaction.timestamp,
                issuerTransaction.operation,
                issuerTransaction.issuerName,
                issuerTransaction.totalShares,
                issuerTransaction.sharePrice,
                issuerTransaction.timeToLive
        )
    }


}