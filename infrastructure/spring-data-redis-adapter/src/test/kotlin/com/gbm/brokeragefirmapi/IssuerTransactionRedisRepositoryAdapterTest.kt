package com.gbm.brokeragefirmapi

import com.gbm.brokeragefirmapi.factory.IssuerTransactionRedisMockFactory.Companion.ISSUER_TRANSACTION_ID
import com.gbm.brokeragefirmapi.factory.IssuerTransactionRedisMockFactory.Companion.createIssuerTransaction
import com.gbm.brokeragefirmapi.factory.IssuerTransactionRedisMockFactory.Companion.createIssuerTransactionRedisEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import java.util.*

class IssuerTransactionRedisRepositoryAdapterTest {

    private lateinit var issuerTransactionRedisRepositoryAdapter: IssuerTransactionRedisRepositoryAdapter

    @Mock
    private lateinit var issuerTransactionRedisRepository: IssuerTransactionRedisRepository

    @BeforeEach
    fun setUp() {

        openMocks(this)

        this.issuerTransactionRedisRepositoryAdapter = IssuerTransactionRedisRepositoryAdapter(this.issuerTransactionRedisRepository)
    }

    @Test
    fun `find by id should return issuer when there is issuer`() {

        `when`(this.issuerTransactionRedisRepository.findById(ISSUER_TRANSACTION_ID))
                .thenReturn(Optional.of(createIssuerTransactionRedisEntity()))


        val issuerTransaction = this.issuerTransactionRedisRepositoryAdapter
                .findById(ISSUER_TRANSACTION_ID)
                ?.orElseThrow()

        assertThat(issuerTransaction).isNotNull
        assertThat(issuerTransaction?.id).isNotNull.isEqualTo(ISSUER_TRANSACTION_ID)

        verify(this.issuerTransactionRedisRepository).findById(ISSUER_TRANSACTION_ID)
    }

    @Test
    fun `create issuer transaction should return created account when account is saved`() {

        this.issuerTransactionRedisRepositoryAdapter.createIssuerTransaction(createIssuerTransaction())

        verify(this.issuerTransactionRedisRepository).save(any(IssuerTransactionRedisEntity::class.java))

    }

}