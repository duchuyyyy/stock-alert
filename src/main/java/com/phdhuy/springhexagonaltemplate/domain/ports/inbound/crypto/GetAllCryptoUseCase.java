package com.phdhuy.springhexagonaltemplate.domain.ports.inbound.crypto;

import com.phdhuy.springhexagonaltemplate.shared.payload.general.ResponseDataAPI;
import org.springframework.data.domain.Pageable;

public interface GetAllCryptoUseCase {

  ResponseDataAPI getAllCrypto(Pageable pageable);
}
