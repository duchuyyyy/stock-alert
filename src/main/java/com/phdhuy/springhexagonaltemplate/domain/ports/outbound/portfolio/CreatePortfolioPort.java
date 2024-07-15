package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.portfolio;

import com.phdhuy.springhexagonaltemplate.domain.model.Portfolio;

public interface CreatePortfolioPort {

  Portfolio createPortfolio(Portfolio portfolio);
}
