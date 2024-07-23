package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetAllAssetPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.AssetSummary;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.AssetRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.AssetMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@PersistenceAdapter
@RequiredArgsConstructor
public class GetAllAssetAdapter implements GetAllAssetPort {

  private final AssetRepository assetRepository;

  private final AssetMapper assetMapper;

  @Override
  public Page<Asset> getAllAsset(Pageable pageable) {
    Page<AssetSummary> assetSummaries = assetRepository.getAllAssetSummary(pageable);

    return assetSummaries.map(assetMapper::toAssetFromProjection);
  }
}
