package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;

public interface CreateCryptoPort {

  void createCrypto(Asset asset);

  void updateCrypto(Asset asset);
}
