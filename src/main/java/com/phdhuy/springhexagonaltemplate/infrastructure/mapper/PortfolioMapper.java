package com.phdhuy.springhexagonaltemplate.infrastructure.mapper;

import com.phdhuy.springhexagonaltemplate.application.rest.response.portfolio.PortfolioInfoResponse;
import com.phdhuy.springhexagonaltemplate.domain.model.Portfolio;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PortfolioEntity;
import com.phdhuy.springhexagonaltemplate.shared.config.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface PortfolioMapper {

  PortfolioEntity fromPortfolioDomain(Portfolio portfolio);

  Portfolio toPortfolioDomain(PortfolioEntity portfolioEntity);

  PortfolioInfoResponse toPortfolioResponse(Portfolio portfolio);
}
