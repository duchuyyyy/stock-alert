package com.phdhuy.springhexagonaltemplate.application.rest.controller.asset;

import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.GetAllAssetUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.GetDetailAssetUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.asset.StreamCryptoPriceUseCase;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import com.phdhuy.springhexagonaltemplate.shared.utils.PagingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("/v1/assets")
@RequiredArgsConstructor
@Tag(name = "Asset APIs")
public class AssetController {

  private final StreamCryptoPriceUseCase streamCryptoPriceUseCase;

  private final GetAllAssetUseCase getAllAssetUseCase;

  private final GetDetailAssetUseCase getDetailAssetUseCase;

  @GetMapping
  public ResponseEntity<ResponseDataAPI> getAllAsset(
      @RequestParam(name = "sort", defaultValue = "rank") String sortBy,
      @RequestParam(name = "order", defaultValue = "desc") String order,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "paging", defaultValue = "50") int paging) {
    Pageable pageable = PagingUtils.makePageRequestWithSnakeCase(sortBy, order, page, paging);
    return ResponseEntity.ok(getAllAssetUseCase.getAllAsset(pageable));
  }

  @GetMapping("/{assetId}")
  public ResponseEntity<ResponseDataAPI> getDetailAsset(@PathVariable UUID assetId) {
    return ResponseEntity.ok(
        ResponseDataAPI.successWithoutMeta(getDetailAssetUseCase.getDetailAsset(assetId)));
  }

  @GetMapping("/prices")
  public Flux<ServerSentEvent<Object>> streamAssetsPrices() {
    return streamCryptoPriceUseCase.streamCryptosPrices();
  }
}
