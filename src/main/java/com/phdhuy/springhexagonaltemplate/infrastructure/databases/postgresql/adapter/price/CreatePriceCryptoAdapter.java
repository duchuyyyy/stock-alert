package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.price;

import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.CreatePriceCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PriceUsdEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.AssetRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.PriceUsdRepository;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreatePriceCryptoAdapter implements CreatePriceCryptoPort {

  private final PriceUsdRepository priceUsdRepository;

  private final AssetRepository assetRepository;

  @Override
  public void createPriceCryptoPort(String identity, double price) {
    PriceUsdEntity priceUsdEntity = new PriceUsdEntity();

    priceUsdEntity.setAssetEntity(
        assetRepository
            .findByIdentity(identity)
            .orElseThrow(() -> new NotFoundException(MessageConstant.ASSET_NOT_FOUND)));
    priceUsdEntity.setPriceUsd(price);

    priceUsdRepository.save(priceUsdEntity);
  }
}
