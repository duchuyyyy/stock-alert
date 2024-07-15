package com.phdhuy.springhexagonaltemplate.domain.services.portfolio;

import com.phdhuy.springhexagonaltemplate.application.rest.request.portfolio.CreatePortfolioRequest;
import com.phdhuy.springhexagonaltemplate.application.rest.response.portfolio.PortfolioInfoResponse;
import com.phdhuy.springhexagonaltemplate.domain.model.Portfolio;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.portfolio.CreatePortfolioUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.portfolio.CreatePortfolioPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.user.FindUserByIdPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.PortfolioMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CreatePortfolioService implements CreatePortfolioUseCase {

  private final CreatePortfolioPort createPortfolioPort;

  private final FindUserByIdPort findUserByIdPort;

  private final PortfolioMapper portfolioMapper;

  @Override
  public PortfolioInfoResponse createPortfolio(
      CreatePortfolioRequest createPortfolioRequest, UUID userId) {
    Portfolio portfolio = new Portfolio();

    portfolio.setName(createPortfolioRequest.getName());
    portfolio.setAvatar(createPortfolioRequest.getAvatar());
    portfolio.setUser(findUserByIdPort.findByUserId(userId));
    portfolio.setDefault(false);

    return portfolioMapper.toPortfolioResponse(createPortfolioPort.createPortfolio(portfolio));
  }
}
