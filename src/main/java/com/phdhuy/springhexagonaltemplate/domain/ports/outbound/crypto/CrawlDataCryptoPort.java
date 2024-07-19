package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;

import java.util.List;

public interface CrawlDataCryptoPort {

  List<Crypto> crawlDataCrypto();
}
