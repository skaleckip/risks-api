package org.norman.risk.service;

import org.norman.risk.dto.RiskWideDto;
import org.norman.risk.repository.RiskRepository;
import org.norman.shared.dto.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class RiskService {
    private final Logger logger = LoggerFactory.getLogger(RiskService.class);
    private final RiskRepository riskRepository;

    public RiskService(RiskRepository riskRepository) {
        this.riskRepository = riskRepository;
    }

    @Transactional(readOnly = true)
    public Optional<RiskWideDto> findRiskWideByIdOptional(UUID id) {
        logger.trace("findRiskByIdOptional: id={}", id);
        return riskRepository.findById(id).map(RiskWideDto::from);
    }

    @Transactional(readOnly = true)
    public PageDto<RiskWideDto> pageRisksBySystemVersionId(
            UUID systemVersionId,
            int pageNumber,
            int pageSize) {
        logger.trace("pageRisksByNames: name={}, pageNumber={}, pageSize={}", systemVersionId, pageNumber, pageSize);
        var page = riskRepository.findBySystemVersionId(systemVersionId, PageRequest.of(pageNumber, pageSize));
        return RiskWideDto.fromPage(page);
    }
}