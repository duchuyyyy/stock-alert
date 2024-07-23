package com.phdhuy.springhexagonaltemplate.application.rest.response.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phdhuy.springhexagonaltemplate.application.rest.response.asset.AssetInfoResponse;
import com.phdhuy.springhexagonaltemplate.domain.model.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInfoResponse {

  private UUID id;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  private int quantity;

  private Double pricePerUnit;

  private Timestamp transactionAt;

  private Double fee;

  private Double total;

  private AssetInfoResponse asset;
}
