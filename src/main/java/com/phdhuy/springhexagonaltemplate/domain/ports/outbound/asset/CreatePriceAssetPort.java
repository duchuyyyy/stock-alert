package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

public interface CreatePriceAssetPort {

  void createPriceAssetPort(String name, String symbol, double price);
}
