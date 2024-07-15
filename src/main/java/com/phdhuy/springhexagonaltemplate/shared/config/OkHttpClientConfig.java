package com.phdhuy.springhexagonaltemplate.shared.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@EnableWebSocket
public class OkHttpClientConfig {

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient();
  }
}
