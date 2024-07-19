package com.phdhuy.springhexagonaltemplate.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Crypto {

  private UUID id;

  private String identity;

  private Long rank;

  private String symbol;

  private String name;

  private Double supply;

  private Double maxSupply;

  private Double marketCapUsd;

  private Double volumeUsd24Hr;

  private Double changePercent24Hr;

  private Double vwap24Hr;

  private String explorer;

  private Double currentPriceUsd;
}
