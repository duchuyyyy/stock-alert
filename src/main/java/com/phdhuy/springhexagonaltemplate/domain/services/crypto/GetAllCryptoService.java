package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto.GetAllCryptoUseCase;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.GetAllCryptoPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.PageInfo;
import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@UseCase
@RequiredArgsConstructor
public class GetAllCryptoService implements GetAllCryptoUseCase {

  private final GetAllCryptoPort getAllCryptoPort;

  @Override
  public ResponseDataAPI getAllCrypto(Pageable pageable) {
    Page<Crypto> cryptos = getAllCryptoPort.getAllCrypto(pageable);

    PageInfo pageInfo =
        new PageInfo(
            pageable.getPageNumber() + 1, cryptos.getTotalPages(), cryptos.getTotalElements());

    return ResponseDataAPI.success(cryptos.getContent().stream().toList(), pageInfo);
  }
}
