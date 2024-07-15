package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.portfolio;

import com.phdhuy.springhexagonaltemplate.domain.model.Portfolio;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.portfolio.CreatePortfolioPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PortfolioEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.PortfolioRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.UserRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.PortfolioMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreatePortfolioAdapter implements CreatePortfolioPort {

  private final PortfolioRepository portfolioRepository;

  private final UserRepository userRepository;

  private final PortfolioMapper portfolioMapper;

  @Override
  public Portfolio createPortfolio(Portfolio portfolio) {
    PortfolioEntity portfolioEntity = new PortfolioEntity();

    portfolioEntity.setName(portfolio.getName());
    portfolioEntity.setDefault(portfolio.isDefault());
    portfolioEntity.setAvatar(portfolio.getAvatar());
    portfolioEntity.setUserEntity(
        userRepository
            .findById(portfolio.getUser().getId())
            .orElseThrow(() -> new NotFoundException(MessageConstant.USER_NOT_FOUND)));

    return portfolioMapper.toPortfolioDomain(portfolioRepository.save(portfolioEntity));
  }
}
