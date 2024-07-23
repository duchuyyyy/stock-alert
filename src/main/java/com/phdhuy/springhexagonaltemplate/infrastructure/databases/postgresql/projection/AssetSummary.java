package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection;

public interface AssetSummary {

  String getId();

  Long getRank();

  String getIdentity();

  String getSymbol();

  String getName();

  Double getSupply();

  Double getMaxSupply();

  Double getMarketCapUsd();

  Double getVolumeUsd24Hr();

  Double getChangePercent24Hr();

  Double getVwap24Hr();

  String getExplorer();

  Double getCurrentPriceUsd();
}
