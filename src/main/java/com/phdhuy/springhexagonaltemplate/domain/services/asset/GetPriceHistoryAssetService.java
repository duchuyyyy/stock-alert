package com.phdhuy.springhexagonaltemplate.domain.services.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.PriceAsset;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.GetPriceHistoryAssetUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetPriceHistoryAssetPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetPriceHistoryAssetService implements GetPriceHistoryAssetUseCase {
  private final GetPriceHistoryAssetPort getPriceHistoryAssetPort;

  @Override
  public List<PriceAsset> getPriceHistoryAsset(UUID assetId, String interval) {
    return getPriceHistoryAssetPort.getPriceHistoryAsset(assetId, interval);
  }
}
