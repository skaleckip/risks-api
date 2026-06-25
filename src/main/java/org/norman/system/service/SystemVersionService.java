package org.norman.system.service;

import org.norman.system.dto.*;
import org.norman.system.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SystemVersionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SystemVersionRepository systemVersionRepository;
    private final RiskAreaRepository riskAreaRepository;
    private final ImpactClassRepository impactClassRepository;
    private final ProbabilityClassRepository probabilityClassRepository;
    private final RiskLevelRepository riskLevelRepository;

    public SystemVersionService(
            SystemVersionRepository systemVersionRepository,
            RiskAreaRepository riskAreaRepository,
            ImpactClassRepository impactClassRepository,
            ProbabilityClassRepository probabilityClassRepository,
            RiskLevelRepository riskLevelRepository) {
        this.systemVersionRepository = systemVersionRepository;
        this.riskAreaRepository = riskAreaRepository;
        this.impactClassRepository = impactClassRepository;
        this.probabilityClassRepository = probabilityClassRepository;
        this.riskLevelRepository = riskLevelRepository;
    }

    @Transactional(readOnly = true)
    public List<SystemVersionDto> listAllSystemVersions() {
        logger.trace("listAllSystemVersions");
        return SystemVersionDto.fromList(systemVersionRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<RiskAreaDto> listAllRiskAreas(UUID systemVersionId) {
        logger.trace("listAllRiskAreas");
        return RiskAreaDto.fromList(
                riskAreaRepository.findBySystemVersionId(systemVersionId));
    }

    @Transactional(readOnly = true)
    public List<ProbabilityClassDto> listAllProbabilityClasses(UUID systemVersionId) {
        logger.trace("listAllProbabilityClasses");
        return ProbabilityClassDto.fromList(
                probabilityClassRepository.findBySystemVersionId(systemVersionId));
    }

    @Transactional(readOnly = true)
    public List<ImpactClassDto> listAllImpactClasses(UUID systemVersionId) {
        logger.trace("listAllImpactClasses");
        return ImpactClassDto.fromList(
                impactClassRepository.findBySystemVersionId(systemVersionId));
    }

    @Transactional(readOnly = true)
    public List<RiskLevelDto> listAllRiskLevels(UUID systemVersionId) {
        logger.trace("listAllRiskLevels");
        return RiskLevelDto.fromList(
                riskLevelRepository.findBySystemVersionId(systemVersionId));
    }
}
