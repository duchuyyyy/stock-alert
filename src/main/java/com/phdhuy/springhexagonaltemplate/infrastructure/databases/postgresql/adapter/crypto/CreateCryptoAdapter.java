package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.crypto;

import com.phdhuy.springhexagonaltemplate.domain.model.Crypto;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.CreateCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.CryptoEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.CryptoRepository;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreateCryptoAdapter implements CreateCryptoPort {

  private final CryptoRepository cryptoRepository;

  @Override
  public void createCrypto(Crypto crypto) {
    CryptoEntity cryptoEntity = new CryptoEntity();

    this.toCryptoEntity(crypto, cryptoEntity);
  }

  @Override
  public void updateCrypto(Crypto crypto) {
    CryptoEntity cryptoEntity =
        cryptoRepository
            .findByIdentity(crypto.getIdentity())
            .orElseThrow(() -> new NotFoundException(MessageConstant.CRYPTO_NOT_FOUND));

    this.toCryptoEntity(crypto, cryptoEntity);
  }

  private void toCryptoEntity(Crypto crypto, CryptoEntity cryptoEntity) {
    cryptoEntity.setIdentity(crypto.getIdentity());
    cryptoEntity.setRank(crypto.getRank());
    cryptoEntity.setSymbol(crypto.getSymbol());
    cryptoEntity.setName(crypto.getName());
    cryptoEntity.setSupply(crypto.getSupply());
    cryptoEntity.setMaxSupply(crypto.getMaxSupply());
    cryptoEntity.setMarketCapUsd(crypto.getMarketCapUsd());
    cryptoEntity.setVolumeUsd24Hr(crypto.getVolumeUsd24Hr());
    cryptoEntity.setChangePercent24Hr(crypto.getChangePercent24Hr());
    cryptoEntity.setVwap24Hr(crypto.getVwap24Hr());
    cryptoEntity.setExplorer(crypto.getExplorer());

    cryptoRepository.save(cryptoEntity);
  }
}
