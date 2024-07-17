package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.messagebroker.RabbitMQPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.external_api.constant.ExternalAPIConstant;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPriceByExternalService extends WebSocketListener {

  private final OkHttpClient okHttpClient;

  private final RabbitMQPort rabbitMQPort;

  @PostConstruct
  public void connect() {
    Request request = new Request.Builder().url(ExternalAPIConstant.GET_PRICE_CRYPTO).build();
    okHttpClient.newWebSocket(request, this);
  }

  @Override
  public void onOpen(WebSocket webSocket, @NotNull Response response) {

    String subscribeMessage =
        "{\"method\": \"SUBSCRIBE\", \"params\": [\"btcusdt@ticker\"], \"id\": 1}";
    webSocket.send(String.valueOf(new TextMessage(subscribeMessage)));
  }

  @Override
  public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
    log.info("WebSocket connection closed: {}", reason);
  }

  @Override
  public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
    try {
      JsonNode node = new ObjectMapper().readTree(text);
      log.info("Received message: {}", node.toString());
      rabbitMQPort.sendMessage(node.toString());
    } catch (JsonProcessingException e) {
      log.error("Error parsing JSON message:", e);
    }
  }

  @Override
  public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
    log.info("WebSocket connection closing: {}", reason);
  }
}
