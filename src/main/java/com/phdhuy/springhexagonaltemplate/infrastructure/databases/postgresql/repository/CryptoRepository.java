package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository;

import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.CryptoEntity;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.CryptoSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, UUID> {

  boolean existsByIdentity(String identity);

  Optional<CryptoEntity> findByIdentity(String identity);

  @Query(
      value =
          "SELECT cast (c.id as varchar) AS id, c.identity as identity, c.symbol as symbol, "
              + "c.name as name, c.supply as supply, c.max_supply as maxSupply, c.rank as rank, "
              + "c.market_cap_usd as marketCapUsd, c.volume_usd24hr as volumeUsd24Hr, "
              + "c.change_percent24hr as changePercent24Hr, c.vwap24hr as vwap24Hr, "
              + "c.explorer as explorer, pu.price_usd as currentPriceUsd "
              + "FROM crypto c "
              + "INNER JOIN ( "
              + "    SELECT p.crypto_id, p.price_usd "
              + "    FROM price_usd p "
              + "    JOIN ( "
              + "        SELECT crypto_id, MAX(created_at) AS max_created_at "
              + "        FROM price_usd "
              + "        GROUP BY crypto_id "
              + "    ) AS latest_price "
              + "    ON p.crypto_id = latest_price.crypto_id "
              + "    AND p.created_at = latest_price.max_created_at "
              + ") AS pu ON pu.crypto_id = c.id",
      nativeQuery = true)
  Page<CryptoSummary> getAllCryptoSummary(Pageable pageable);
}
