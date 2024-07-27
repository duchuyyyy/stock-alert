package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.PriceAsset;

import java.util.List;
import java.util.UUID;

public interface GetPriceHistoryAssetUseCase {

  List<PriceAsset> getPriceHistoryAsset(UUID assetId, String interval);
}
