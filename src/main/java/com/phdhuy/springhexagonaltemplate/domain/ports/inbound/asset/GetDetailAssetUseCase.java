package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;

import java.util.UUID;

public interface GetDetailAssetUseCase {

  Asset getDetailAsset(UUID asset);
}
