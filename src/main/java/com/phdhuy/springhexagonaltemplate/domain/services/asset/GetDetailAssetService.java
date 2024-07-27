package com.phdhuy.springhexagonaltemplate.domain.services.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.GetDetailAssetUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetDetailAssetPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetDetailAssetService implements GetDetailAssetUseCase {

  private final GetDetailAssetPort getDetailAssetPort;

  @Override
  public Asset getDetailAsset(UUID asset) {
    return getDetailAssetPort.getDetailAsset(asset);
  }
}
