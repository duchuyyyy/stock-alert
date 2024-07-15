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
@Table(name = "crypto")
public class CryptoEntity extends BaseEntity {

  @Id @GeneratedValue private UUID id;

  @Column private String identity;

  @Column private Long rank;

  @Column private String symbol;

  @Column private String name;

  @Column private Double supply;

  @Column private Double maxSupply;

  @Column private Double marketCapUsd;

  @Column private Double volumeUsd24Hr;

  @Column private Double changePercent24Hr;

  @Column private Double vwap24Hr;

  @Column private Double explorer;
}
