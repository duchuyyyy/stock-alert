package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.CreateCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.ExistsCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.GetDataCryptoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoInfoSyncJob {

  private final CreateCryptoPort createCryptoPort;

  private final GetDataCryptoPort getDataCryptoPort;

  private final ExistsCryptoPort existsCryptoPort;

  @Scheduled(cron = "0 */1 * * * *")
  public void crawlDataCryptoAndSaveToDB() {
    List<Crypto> cryptoList = getDataCryptoPort.getDataCrypto();
    for (Crypto crypto : cryptoList) {
      if (existsCryptoPort.existsByIdentity(crypto.getIdentity())) {
        createCryptoPort.updateCrypto(crypto);
      } else {
        createCryptoPort.createCrypto(crypto);
      }
    }
  }
}
