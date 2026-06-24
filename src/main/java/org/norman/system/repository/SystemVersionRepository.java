package org.norman.system.repository;

import org.norman.system.entity.SystemVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemVersionRepository extends JpaRepository<SystemVersion, UUID> {
}
