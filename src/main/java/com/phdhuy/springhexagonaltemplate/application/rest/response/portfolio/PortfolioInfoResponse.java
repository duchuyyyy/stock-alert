package com.phdhuy.springhexagonaltemplate.application.rest.response.portfolio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phdhuy.springhexagonaltemplate.application.rest.response.transaction.TransactionInfoResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PortfolioInfoResponse {

  private UUID id;

  private String name;

  private String avatar;

  private boolean isDefault;

  private List<TransactionInfoResponse> transactions;
}
