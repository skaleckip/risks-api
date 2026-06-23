package org.norman.metadata.service;

import org.norman.metadata.dto.RiskAreaDto;
import org.norman.metadata.repository.RiskAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetaService {
    private final Logger logger = LoggerFactory.getLogger(MetaService.class);
    private final RiskAreaRepository riskAreaRepository;

    public MetaService(RiskAreaRepository riskAreaRepository) {
        this.riskAreaRepository = riskAreaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<RiskAreaDto> findRiskAreaByIdOptional(UUID id) {
        logger.trace("findRiskAreaByIdOptional: id={}", id);
        return riskAreaRepository.findById(id).map(RiskAreaDto::from);
    }

    @Transactional(readOnly = true)
    public Optional<RiskAreaDto> findRiskAreaByCodeOptional(String code) {
        logger.trace("findRiskAreaByCodeOptional: code={}", code);
        return riskAreaRepository.findByCode(code).map(RiskAreaDto::from);
    }

    @Transactional(readOnly = true)
    public List<RiskAreaDto> listAllRiskAreas() {
        logger.trace("listAllRiskAreas");
        return RiskAreaDto.fromList(riskAreaRepository.findAll());
    }
}