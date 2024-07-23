package com.phdhuy.springhexagonaltemplate.domain.services.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.CreateCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.ExistsCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.CrawlDataCryptoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoInfoSyncJob {

  private final CreateCryptoPort createCryptoPort;

  private final CrawlDataCryptoPort crawlDataCryptoPort;

  private final ExistsCryptoPort existsCryptoPort;

  @Scheduled(cron = "0 */1 * * * *")
  public void crawlDataCryptoAndSaveToDB() {
    List<Asset> assetList = crawlDataCryptoPort.crawlDataCrypto();
    for (Asset asset : assetList) {
      if (existsCryptoPort.existsByIdentity(asset.getIdentity())) {
        createCryptoPort.updateCrypto(asset);
      } else {
        createCryptoPort.createCrypto(asset);
      }
    }
  }
}
