package com.phdhuy.springhexagonaltemplate.domain.ports.outbound.crypto;

public interface ExistsCryptoPort {

  boolean existsByIdentity(String identity);
}
