package org.norman.system.repository;

import org.norman.system.entity.RiskArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RiskAreaRepository extends JpaRepository<RiskArea, UUID> {
    List<RiskArea> findBySystemVersionId(UUID systemVersionId);
}