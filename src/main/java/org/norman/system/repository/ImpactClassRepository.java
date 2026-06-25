package org.norman.system.repository;

import org.norman.system.entity.ImpactClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImpactClassRepository extends JpaRepository<ImpactClass, UUID> {
    List<ImpactClass> findBySystemVersionId(UUID systemVersionId);
}
