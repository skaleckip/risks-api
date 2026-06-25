package org.norman.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.system.dto.*;
import org.norman.system.service.SystemVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Norman")
public class SystemVersionController {
    private final Logger logger = LoggerFactory.getLogger(SystemVersionController.class);
    private final SystemVersionService systemVersionService;

    public SystemVersionController(SystemVersionService systemVersionService) {
        this.systemVersionService = systemVersionService;
    }

    @GetMapping("/system-versions")
    @Operation(summary = "List all the system versions")
    public List<SystemVersionDto> listAllSystemVersions() {
        logger.trace("listAllSystemVersions");
        return systemVersionService.listAllSystemVersions();
    }

    @GetMapping("/system-versions/{systemVersionId}/risk-areas")
    @Operation(summary = "List all the system version risk areas")
    public List<RiskAreaDto> listAllRiskAreas(
            @PathVariable UUID systemVersionId) {
        logger.trace("listAllRiskAreas");
        return systemVersionService.listAllRiskAreas(systemVersionId);
    }

    @GetMapping("/system-versions/{systemVersionId}/impact-classes")
    @Operation(summary = "List all the system version impact classes")
    public List<ImpactClassDto> listAllImpactClasses(
            @PathVariable UUID systemVersionId) {
        logger.trace("listAllImpactClasses");
        return systemVersionService.listAllImpactClasses(systemVersionId);
    }

    @GetMapping("/system-versions/{systemVersionId}/probability-classes")
    @Operation(summary = "List all the system version probability classes")
    public List<ProbabilityClassDto> listAllProbabilityClasses(
            @PathVariable UUID systemVersionId) {
        logger.trace("listAllProbabilityClasses");
        return systemVersionService.listAllProbabilityClasses(systemVersionId);
    }

    @GetMapping("/system-versions/{systemVersionId}/risk-levels")
    @Operation(summary = "List all the system version risk levels")
    public List<RiskLevelDto> listAllRiskLevels(
            @PathVariable UUID systemVersionId) {
        logger.trace("listAllRiskLevels");
        return systemVersionService.listAllRiskLevels(systemVersionId);
    }
}
