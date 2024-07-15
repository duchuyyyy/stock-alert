package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.crypto;

import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.ExistsCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.CryptoRepository;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExistsCryptoAdapter implements ExistsCryptoPort {

  private final CryptoRepository cryptoRepository;

  @Override
  public boolean existsByIdentity(String identity) {
    return cryptoRepository.existsByIdentity(identity);
  }
}
