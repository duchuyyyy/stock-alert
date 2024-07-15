package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity;

import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

  @Id @GeneratedValue private UUID id;

  @Enumerated(EnumType.STRING)
  @Column
  private TransactionType type;

  @Column private int quantity;

  @Column private Double pricePerUnit;

  @Column private Timestamp transactionAt;

  @Column private Double fee;

  @Column private Double total;

  @ManyToOne
  @JoinColumn(name = "portfolio_id")
  private PortfolioEntity portfolioEntity;
}
