package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;

import java.util.List;

public interface CrawlDataCryptoPort {

  List<Asset> crawlDataCrypto();
}
