package org.norman.risks.risk.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiskRepository extends JpaRepository<Risk, UUID> {
    Page<Risk> findByNameLike(
            String name,
            Pageable page);
}