package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.CreatePriceCryptoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceListenerService {

  private final CreatePriceCryptoPort createPriceCryptoPort;

  private final ObjectMapper objectMapper;

  @RabbitListener(queues = "price.database")
  public void receiveMessage(String message) {
    try {
      JsonNode rootNode = objectMapper.readTree(message);

      rootNode
          .fields()
          .forEachRemaining(
              entry -> {
                String cryptoName = entry.getKey();
                double price = entry.getValue().asDouble();
                createPriceCryptoPort.createPriceCryptoPort(cryptoName, price);
              });

    } catch (Exception e) {
      log.error("Error processing message:", e);
    }
  }
}
