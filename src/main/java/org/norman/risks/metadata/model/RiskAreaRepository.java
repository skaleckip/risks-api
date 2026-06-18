package org.norman.risks.metadata.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiskAreaRepository extends JpaRepository<RiskArea, UUID> {

    @SuppressWarnings("UnnecessaryModifier")
    public Optional<RiskArea> findByCode(String code);
}