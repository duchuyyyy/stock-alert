package com.phdhuy.springhexagonaltemplate.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
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

  private List<Double> pricesUsd;
}
