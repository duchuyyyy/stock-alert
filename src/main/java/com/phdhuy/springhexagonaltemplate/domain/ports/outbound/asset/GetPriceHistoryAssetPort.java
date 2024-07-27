package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.PriceAsset;

import java.util.List;
import java.util.UUID;

public interface GetPriceHistoryAssetPort {

  List<PriceAsset> getPriceHistoryAsset(UUID assetId, String interval);
}
