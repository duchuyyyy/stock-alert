package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.price;

import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto.CreatePriceCryptoPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PriceUsdEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.CryptoRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.PriceUsdRepository;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import com.phdhuy.springhexagonaltemplate.shared.constant.MessageConstant;
import com.phdhuy.springhexagonaltemplate.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreatePriceCryptoAdapter implements CreatePriceCryptoPort {

  private final PriceUsdRepository priceUsdRepository;

  private final CryptoRepository cryptoRepository;

  @Override
  public void createPriceCryptoPort(String identity, double price) {
    PriceUsdEntity priceUsdEntity = new PriceUsdEntity();

    priceUsdEntity.setCryptoEntity(
        cryptoRepository
            .findByIdentity(identity)
            .orElseThrow(() -> new NotFoundException(MessageConstant.CRYPTO_NOT_FOUND)));
    priceUsdEntity.setPriceUsd(price);

    priceUsdRepository.save(priceUsdEntity);
  }
}
