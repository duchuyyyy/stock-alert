package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;

public interface CreateCryptoPort {

  void createCrypto(Crypto crypto);

  void updateCrypto(Crypto crypto);
}
