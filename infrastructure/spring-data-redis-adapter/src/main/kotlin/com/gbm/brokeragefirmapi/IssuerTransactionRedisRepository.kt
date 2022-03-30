package com.gbm.brokeragefirmapi

import org.springframework.data.keyvalue.repository.KeyValueRepository

interface IssuerTransactionRedisRepository : KeyValueRepository<IssuerTransactionRedisEntity, String>