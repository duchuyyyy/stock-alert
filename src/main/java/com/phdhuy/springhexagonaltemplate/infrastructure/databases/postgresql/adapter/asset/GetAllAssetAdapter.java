package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.adapter.asset;

import com.phdhuy.springhexagonaltemplate.domain.model.Asset;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetAllAssetPort;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetLatestPriceAssetPort;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.projection.AssetSummary;
import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository.AssetRepository;
import com.phdhuy.springhexagonaltemplate.infrastructure.mapper.AssetMapper;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class GetAllAssetAdapter implements GetAllAssetPort {

  private final AssetRepository assetRepository;

  private final GetLatestPriceAssetPort getLatestPriceAssetPort;

  private final AssetMapper assetMapper;

  @Override
  public Page<Asset> getAllAsset(Pageable pageable) {
    log.info("start query db");
    Page<AssetSummary> assetSummaries = assetRepository.getAllAssetSummary(pageable);
    log.info("end query db");
    List<String> symbols =
        assetSummaries.getContent().stream().map(AssetSummary::getIdentity).toList();

    log.info("start query influxdb");
    HashMap<String, Double> latestPrices = getLatestPriceAssetPort.getLatestPriceAssets(symbols);
    log.info("end query influxdb");

    log.info("start convert to response");
    List<Asset> assets =
        assetSummaries.getContent().stream()
            .map(summary -> mapToAsset(summary, latestPrices))
            .toList();
    log.info("end convert to response");

    return new PageImpl<>(assets, pageable, assetSummaries.getTotalElements());
  }

  private Asset mapToAsset(AssetSummary assetSummary, HashMap<String, Double> latestPrices) {
    Double latestPrice = latestPrices.get(assetSummary.getIdentity());
    return assetMapper.toAssetFromProjection(assetSummary, latestPrice != null ? latestPrice : 0.0);
  }
}
