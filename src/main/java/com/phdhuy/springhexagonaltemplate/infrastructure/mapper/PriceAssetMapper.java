package com.phdhuy.springhexagonaltemplate.infrastructure.mapper;

import com.phdhuy.springhexagonaltemplate.domain.model.PriceAsset;
import com.phdhuy.springhexagonaltemplate.shared.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(config = MapStructConfig.class)
public interface PriceAssetMapper {

  @Mapping(source = "price", target = "price")
  @Mapping(source = "time", target = "time")
  PriceAsset toPriceAsset(Double price, Timestamp time);
}
