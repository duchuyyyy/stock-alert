package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.asset;

public interface ExistsCryptoPort {

  boolean existsByIdentity(String identity);
}
