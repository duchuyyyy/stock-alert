package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetAllCryptoPort {

  Page<Crypto> getAllCrypto(Pageable pageable);
}
