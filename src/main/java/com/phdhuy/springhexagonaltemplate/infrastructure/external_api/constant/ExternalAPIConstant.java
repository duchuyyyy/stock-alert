package com.phdhuy.springhexagonaltemplate.infrastructure.external_api.constant;

public class ExternalAPIConstant {

  private ExternalAPIConstant() {}

  public static final String GET_ALL_INFO_CRYPTO = "https://api.coincap.io/v2/assets";

  public static final String GET_PRICE_CRYPTO = "wss://ws.coincap.io/prices?assets=ALL";
}
