package com.phdhuy.springhexagonaltemplate.infrastructure.mapper;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.CryptoSummary;
import com.phdhuy.springhexagonaltemplate.shared.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface CryptoMapper {

  @Mapping(source = "cryptoSummary.rank", target = "rank")
  @Mapping(source = "cryptoSummary.currentPriceUsd", target = "currentPriceUsd")
  Crypto toCryptoFromProjection(CryptoSummary cryptoSummary);
}
