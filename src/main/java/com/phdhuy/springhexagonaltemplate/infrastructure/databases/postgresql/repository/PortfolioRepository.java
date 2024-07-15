package com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.repository;

import com.phdhuy.springhexagonaltemplate.infrastructure.databases.postgresql.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, UUID> {}
