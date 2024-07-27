package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;

import java.util.UUID;

public interface GetDetailAssetPort {

  Asset getDetailAsset(UUID id);
}
