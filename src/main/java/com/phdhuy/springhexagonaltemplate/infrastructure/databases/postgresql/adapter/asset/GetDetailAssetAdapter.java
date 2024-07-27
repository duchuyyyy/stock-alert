package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetDetailAssetPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetLatestPriceAssetPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.AssetEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.AssetRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.AssetMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetDetailAssetAdapter implements GetDetailAssetPort {

  private final AssetRepository assetRepository;

  private final GetLatestPriceAssetPort getLatestPriceAssetPort;

  private final AssetMapper assetMapper;

  @Override
  public Asset getDetailAsset(UUID id) {
    AssetEntity assetEntity =
        assetRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(MessageConstant.ASSET_NOT_FOUND));
    return assetMapper.toAssetFromAssetEntity(
        assetEntity, getLatestPriceAssetPort.getLatestPriceAsset(assetEntity.getIdentity()));
  }
}
