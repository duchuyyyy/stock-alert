package com.phdhuy.springhexagonaltemplate.application.rest.controller.crypto;

import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto.GetAllCryptoUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto.StreamCryptoPriceUseCase;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import com.phdhuy.springhexagonaltemplate.shared.utils.PagingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/cryptos")
@RequiredArgsConstructor
@Tag(name = "Cryptos APIs")
public class CryptoController {

  private final StreamCryptoPriceUseCase streamCryptoPriceUseCase;

  private final GetAllCryptoUseCase getAllCryptoUseCase;

  @GetMapping
  public ResponseEntity<ResponseDataAPI> getAllCryptos(
      @RequestParam(name = "sort", defaultValue = "rank") String sortBy,
      @RequestParam(name = "order", defaultValue = "desc") String order,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "paging", defaultValue = "50") int paging) {
    Pageable pageable = PagingUtils.makePageRequestWithSnakeCase(sortBy, order, page, paging);
    return ResponseEntity.ok(getAllCryptoUseCase.getAllCrypto(pageable));
  }

  @GetMapping("/prices")
  public Flux<ServerSentEvent<Object>> streamCryptosPrices() {
    return streamCryptoPriceUseCase.streamCryptosPrices();
  }
}
