package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;

import java.util.List;

public interface GetDataCryptoPort {

  List<Crypto> getDataCrypto();
}
