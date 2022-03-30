package com.gbm.brokeragefirmapi.port.secondary;

import com.gbm.brokeragefirmapi.domain.model.IssuerTransaction;

import java.util.Optional;

public interface IssuerTransactionRepositoryPort {

    Optional<IssuerTransaction> findById(String id);

    void createIssuerTransaction(IssuerTransaction issuerTransaction);
}
