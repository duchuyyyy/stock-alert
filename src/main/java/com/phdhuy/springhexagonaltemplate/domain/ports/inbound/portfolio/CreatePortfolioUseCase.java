package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.portfolio;

import com.phdhuy.springhexagonaltemplate.application.rest.request.portfolio.CreatePortfolioRequest;
import com.phdhuy.springhexagonaltemplate.application.rest.response.portfolio.PortfolioInfoResponse;

import java.util.UUID;

public interface CreatePortfolioUseCase {

  PortfolioInfoResponse createPortfolio(CreatePortfolioRequest createPortfolioRequest, UUID userId);
}
