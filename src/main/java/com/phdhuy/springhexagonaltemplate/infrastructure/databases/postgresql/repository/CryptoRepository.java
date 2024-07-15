package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository;

import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, UUID> {

  boolean existsByIdentity(String identity);

  Optional<CryptoEntity> findByIdentity(String identity);
}
