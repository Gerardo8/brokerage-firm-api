package com.gbm.brokeragefirmapi

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction

class IssuerTransactionRedisMapper {

    companion object {

        @JvmStatic
        fun issuerTransactionFrom(issuerTransactionRedisEntity: IssuerTransactionRedisEntity) = IssuerTransaction(
            issuerTransactionRedisEntity.id,
            issuerTransactionRedisEntity.issuerName,
            issuerTransactionRedisEntity.totalShares,
            issuerTransactionRedisEntity.sharePrice,
            issuerTransactionRedisEntity.timestamp,
            issuerTransactionRedisEntity.operation,
            issuerTransactionRedisEntity.timeToLive
        )

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