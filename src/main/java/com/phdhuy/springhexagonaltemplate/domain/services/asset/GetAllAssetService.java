package com.phdhuy.springhexagonaltemplate.domain.services.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.GetAllAssetUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetAllAssetPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.PageInfo;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetAllAssetService implements GetAllAssetUseCase {

  private final GetAllAssetPort getAllAssetPort;

  @Override
  public ResponseDataAPI getAllAsset(Pageable pageable) {
    Page<Asset> assets = getAllAssetPort.getAllAsset(pageable);

    PageInfo pageInfo =
        new PageInfo(
            pageable.getPageNumber() + 1, assets.getTotalPages(), assets.getTotalElements());

    return ResponseDataAPI.success(assets.getContent().stream().toList(), pageInfo);
  }
}
