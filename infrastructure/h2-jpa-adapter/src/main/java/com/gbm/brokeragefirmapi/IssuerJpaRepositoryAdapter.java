package com.gbm.brokeragefirmapi;

import com.gbm.brokeragefirmapi.domain.model.Issuer;
import com.gbm.brokeragefirmapi.port.secondary.IssuerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.gbm.brokeragefirmapi.IssuerJpaMapper.issuerJpaEntityFrom;
import static java.util.stream.Collectors.toList;

@Component
@Transactional
@RequiredArgsConstructor
public class IssuerJpaRepositoryAdapter implements IssuerRepositoryPort {

    private final IssuerJpaRepository issuerJpaRepository;

    @Override
    public Optional<Issuer> findByAccountIdAndStockId(final Long accountId, final Integer stockId) {

        return this.issuerJpaRepository.findByAccountIdAndStockId(accountId, stockId)
                .map(IssuerJpaMapper::issuerFrom);
    }

    @Override
    public void createIssuer(final Issuer issuer) {

        final var issuerJpaEntity = issuerJpaEntityFrom(issuer);
        this.issuerJpaRepository.save(issuerJpaEntity);
    }

    @Override
    public List<Issuer> findAllByAccountId(final Long accountId) {

        return this.issuerJpaRepository.findAllByAccountId(accountId)
                .stream()
                .map(IssuerJpaMapper::issuerFrom)
                .collect(toList());
    }
}
