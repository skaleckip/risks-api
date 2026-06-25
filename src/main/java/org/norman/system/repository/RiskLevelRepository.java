package org.norman.system.repository;

import org.norman.system.entity.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RiskLevelRepository extends JpaRepository<RiskLevel, UUID> {
    List<RiskLevel> findBySystemVersionId(UUID systemVersionId);
}
