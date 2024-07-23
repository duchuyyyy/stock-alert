package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price_usd")
public class PriceUsdEntity extends BaseEntity {

  @Id @GeneratedValue private UUID id;

  @Column private Double priceUsd;

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private AssetEntity assetEntity;
}
