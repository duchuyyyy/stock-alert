package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto.StreamCryptoPriceUseCase;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@UseCase
@Slf4j
public class StreamCryptoPriceService implements StreamCryptoPriceUseCase {

  private final ObjectMapper objectMapper;

  private FluxSink<JsonNode> messageSink;

  private final Flux<JsonNode> messageFlux;

  public StreamCryptoPriceService(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
      this.messageFlux =
        Flux.create(emitter -> this.messageSink = emitter, FluxSink.OverflowStrategy.BUFFER);
  }

  @Override
  public Flux<ServerSentEvent<Object>> streamCryptosPrices() {
    return messageFlux.map(
        data ->
            ServerSentEvent.builder()
                .id(String.valueOf(data.hashCode()))
                .event("stream-price")
                .data(data)
                .build());
  }

  @RabbitListener(queues = "price.client")
  public void receiveMessage(String message) {
    try {
      JsonNode rootNode = objectMapper.readTree(message);

      if (messageSink != null) {
        messageSink.next(rootNode);
      }
    } catch (Exception e) {
      log.error("Error processing message:", e);
    }
  }
}
