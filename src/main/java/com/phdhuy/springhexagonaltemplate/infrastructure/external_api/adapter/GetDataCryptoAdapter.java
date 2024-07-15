package com.phdhuy.springhexagonaltemplate.infrastructure.external_api.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.GetDataCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.external_api.constant.ExternalAPIConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetDataCryptoAdapter implements GetDataCryptoPort {

  private final OkHttpClient httpClient;

  private final ObjectMapper objectMapper;

  @Override
  public List<Crypto> getDataCrypto() {
    List<Crypto> cryptoList = new ArrayList<>();

    Request request = new Request.Builder().url(ExternalAPIConstant.GET_ALL_INFO_CRYPTO).build();

    try {
      Response response = httpClient.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }

      String responseBody = response.body().string();
      JsonNode root = objectMapper.readTree(responseBody);
      JsonNode dataNode = root.path("data");

      for (JsonNode cryptoNode : dataNode) {
        cryptoList.add(this.convertToCrypto(cryptoNode));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return cryptoList;
  }

  private Crypto convertToCrypto(JsonNode cryptoNode) {
    Crypto crypto = new Crypto();

    crypto.setIdentity(cryptoNode.get("id").asText());
    crypto.setRank(cryptoNode.get("rank").asLong());
    crypto.setSymbol(cryptoNode.get("symbol").asText());
    crypto.setName(cryptoNode.get("name").asText());
    crypto.setSupply(cryptoNode.get("supply").asDouble());
    crypto.setMaxSupply(cryptoNode.get("maxSupply").asDouble());
    crypto.setMarketCapUsd(cryptoNode.get("marketCapUsd").asDouble());
    crypto.setVolumeUsd24Hr(cryptoNode.get("volumeUsd24Hr").asDouble());
    crypto.setChangePercent24Hr(cryptoNode.get("changePercent24Hr").asDouble());
    crypto.setVwap24Hr(cryptoNode.get("vwap24Hr").asDouble());
    crypto.setExplorer(cryptoNode.get("explorer").asText());

    return crypto;
  }
}
