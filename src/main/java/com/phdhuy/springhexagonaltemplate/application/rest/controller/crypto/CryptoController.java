package com.phdhuy.springhexagonaltemplate.application.rest.controller.crypto;

import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto.StreamCryptoPriceUseCase;
import com.phdhuy.springhexagonaltemplate.infrastructure.security.domain.UserPrincipal;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import com.phdhuy.springhexagonaltemplate.shared.utils.PagingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

  @GetMapping
  public ResponseEntity<ResponseDataAPI> getCryptos(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestParam(name = "sort", defaultValue = "created_at") String sortBy,
      @RequestParam(name = "order", defaultValue = "desc") String order,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "paging", defaultValue = "10") int paging) {
    return null;
  }

  @GetMapping("/prices")
  public Flux<ServerSentEvent<Object>> streamCryptosPrices() {
    return streamCryptoPriceUseCase.streamCryptosPrices();
  }
}
