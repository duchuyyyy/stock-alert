package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset;

import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import org.springframework.data.domain.Pageable;

public interface GetAllAssetUseCase {

  ResponseDataAPI getAllAsset(Pageable pageable);
}
