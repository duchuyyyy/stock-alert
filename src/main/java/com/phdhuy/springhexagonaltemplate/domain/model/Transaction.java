package com.phdhuy.springhexagonaltemplate.domain.model;

import com.phdhuy.springhexagonaltemplate.domain.model.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class Transaction {

  private UUID id;

  private Timestamp createdAt;

  private TransactionType transactionType;

  private int quantity;

  private Double pricePerUnit;

  private Timestamp transactionAt;

  private Double fee;

  private Double total;

  private Asset asset;
}
