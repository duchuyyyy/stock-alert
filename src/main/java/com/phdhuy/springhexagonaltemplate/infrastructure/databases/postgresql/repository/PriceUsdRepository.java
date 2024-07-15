package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository;

import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PriceUsdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceUsdRepository extends JpaRepository<PriceUsdEntity, UUID> {}
