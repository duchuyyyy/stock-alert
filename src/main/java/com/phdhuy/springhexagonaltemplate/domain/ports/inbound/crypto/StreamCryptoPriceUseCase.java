package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface StreamCryptoPriceUseCase {
  Flux<ServerSentEvent<Object>> streamCryptosPrices();
}
