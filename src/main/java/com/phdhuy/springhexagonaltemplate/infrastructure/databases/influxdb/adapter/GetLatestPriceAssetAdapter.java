package com.phdhuy.springhexagonaltemplate.infrastructure.databases.influxdb.adapter;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset.GetLatestPriceAssetPort;
import com.phdhuy.springhexagonaltemplate.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class GetLatestPriceAssetAdapter implements GetLatestPriceAssetPort {

  private final InfluxDBClient influxDBClient;

  @Override
  public Double getLatestPriceAsset(String symbol) {
    QueryApi queryApi = influxDBClient.getQueryApi();
    String fluxQuery = buildFluxQuery(symbol);

    List<FluxTable> tables = queryApi.query(fluxQuery);

    if (tables.isEmpty()) {
      return 0.0;
    }

    FluxTable table = tables.get(0);
    if (table.getRecords().isEmpty()) {
      return 0.0;
    }

    FluxRecord fluxRecord = table.getRecords().get(0);
    return fluxRecord.getValueByKey("_value") != null
        ? ((Number) Objects.requireNonNull(fluxRecord.getValueByKey("_value"))).doubleValue()
        : 0.0;
  }

  private String buildFluxQuery(String symbol) {
    return String.format(
        "from(bucket: \"stock-alert\") "
            + "|> range(start: -1y) "
            + "|> filter(fn: (r) => r._measurement == \"price_asset\" and r.symbol == \"%s\") "
            + "|> sort(columns: [\"_time\"], desc: true) "
            + "|> limit(n:1)",
        symbol);
  }
}
