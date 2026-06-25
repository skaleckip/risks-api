package org.norman.system.repository;

import org.norman.system.entity.ProbabilityClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProbabilityClassRepository extends JpaRepository<ProbabilityClass, UUID> {
    List<ProbabilityClass> findBySystemVersionId(UUID systemVersionId);
}
