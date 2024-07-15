package com.phdhuy.springhexagonaltemplate.domain.services.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.CreateCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.ExistsCryptoPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.GetDataCryptoPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CryptoSyncJob {

  private final CreateCryptoPort createCryptoPort;

  private final GetDataCryptoPort getDataCryptoPort;

  private final ExistsCryptoPort existsCryptoPort;

  @Scheduled(cron = "*/10 * * * * *")
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
