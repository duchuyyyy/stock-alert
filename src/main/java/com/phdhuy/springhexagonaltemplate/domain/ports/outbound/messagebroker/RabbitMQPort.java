package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.messagebroker;

public interface RabbitMQPort {

  void sendMessage(String message);
}
