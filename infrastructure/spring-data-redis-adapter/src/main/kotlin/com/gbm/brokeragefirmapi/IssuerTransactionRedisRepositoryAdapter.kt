package com.gbm.brokeragefirmapi

import com.gbm.brokeragefirmapi.IssuerTransactionRedisMapper.Companion.issuerTransactionFrom
import com.gbm.brokeragefirmapi.IssuerTransactionRedisMapper.Companion.issuerTransactionRedisEntityFrom
import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction
import com.gbm.brokeragefirmapi.port.secondary.IssuerTransactionRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class IssuerTransactionRedisRepositoryAdapter(val issuerTransactionRedisRepository: IssuerTransactionRedisRepository) : IssuerTransactionRepository {

    override fun findById(id: String): Optional<IssuerTransaction>? =
            this.issuerTransactionRedisRepository
                    .findById(id)
                    .map { issuerTransactionFrom(it) }


    override fun createIssuerTransaction(issuerTransaction: IssuerTransaction) {

        val issuerTransactionRedisEntity = issuerTransactionRedisEntityFrom(issuerTransaction)
        this.issuerTransactionRedisRepository.save(issuerTransactionRedisEntity)

    }
}