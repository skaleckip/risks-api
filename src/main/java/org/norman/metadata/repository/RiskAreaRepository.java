package org.norman.metadata.repository;

import org.norman.metadata.entity.RiskArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiskAreaRepository extends JpaRepository<RiskArea, UUID> {

    Optional<RiskArea> findByCode(String code);
}