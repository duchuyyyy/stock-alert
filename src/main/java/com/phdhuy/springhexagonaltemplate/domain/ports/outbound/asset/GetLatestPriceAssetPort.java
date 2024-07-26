package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

public interface GetLatestPriceAssetPort {

  Double getLatestPriceAsset(String symbol);
}
