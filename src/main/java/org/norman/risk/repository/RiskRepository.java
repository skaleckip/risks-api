package org.norman.risk.repository;

import org.norman.risk.entity.Risk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiskRepository extends JpaRepository<Risk, UUID> {
    Page<Risk> findBySystemVersionId(UUID systemVersionId, Pageable page);
    Page<Risk> findByOwnerUsername(String ownerUsername, Pageable page);
}