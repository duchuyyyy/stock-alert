package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.asset;

import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.ExistsCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.AssetRepository;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExistsAssetAdapter implements ExistsCryptoPort {

  private final AssetRepository assetRepository;

  @Override
  public boolean existsByIdentity(String identity) {
    return assetRepository.existsByIdentity(identity);
  }
}
