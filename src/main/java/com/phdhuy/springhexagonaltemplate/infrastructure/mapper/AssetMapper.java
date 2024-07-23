package com.phdhuy.springhexagonaltemplate.infrastructure.mapper;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.AssetSummary;
import com.phdhuy.springhexagonaltemplate.shared.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface AssetMapper {

  @Mapping(source = "assetSummary.rank", target = "rank")
  @Mapping(source = "assetSummary.currentPriceUsd", target = "currentPriceUsd")
  Asset toAssetFromProjection(AssetSummary assetSummary);
}
