package com.phdhuy.springhexagonaltemplate.application.rest.controller.portfolio;

import com.phdhuy.springhexagonaltemplate.application.rest.request.portfolio.CreatePortfolioRequest;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.portfolio.CreatePortfolioUseCase;
import com.phdhuy.springhexagonaltemplate.infrastructure.security.domain.UserPrincipal;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/portfolios")
@RequiredArgsConstructor
@Tag(name = "Portfolio APIs")
public class PortfolioController {

  private final CreatePortfolioUseCase createPortfolioUseCase;

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ResponseDataAPI> createPortfolio(
      @Valid @RequestBody CreatePortfolioRequest createPortfolioRequest,
      @AuthenticationPrincipal UserPrincipal userPrincipal) {
    return ResponseEntity.ok(
        ResponseDataAPI.successWithoutMeta(
            createPortfolioUseCase.createPortfolio(createPortfolioRequest, userPrincipal.getId())));
  }
}
