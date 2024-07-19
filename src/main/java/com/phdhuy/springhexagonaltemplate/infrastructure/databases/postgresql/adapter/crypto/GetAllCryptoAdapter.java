package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.GetAllCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.CryptoSummary;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.CryptoRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.CryptoMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@PersistenceAdapter
@RequiredArgsConstructor
public class GetAllCryptoAdapter implements GetAllCryptoPort {

  private final CryptoRepository cryptoRepository;

  private final CryptoMapper cryptoMapper;

  @Override
  public Page<Crypto> getAllCrypto(Pageable pageable) {
    Page<CryptoSummary> cryptoSummaries = cryptoRepository.getAllCryptoSummary(pageable);

    return cryptoSummaries.map(cryptoMapper::toCryptoFromProjection);
  }
}
