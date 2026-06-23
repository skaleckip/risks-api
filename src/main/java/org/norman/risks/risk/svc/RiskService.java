package org.norman.risks.risk.svc;

import org.norman.risks.risk.dto.ImpactClassDto;
import org.norman.risks.risk.dto.ProbabilityClassDto;
import org.norman.risks.risk.dto.RiskWideDto;
import org.norman.risks.risk.model.ImpactClass;
import org.norman.risks.risk.model.ProbabilityClass;
import org.norman.risks.risk.model.Risk;
import org.norman.risks.risk.model.RiskRepository;
import org.norman.risks.shared.dto.PageOfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return riskRepository.findById(id).map(this::toRiskWideDto);
    }

    @Transactional(readOnly = true)
    public PageOfDto<RiskWideDto> pageRisksBySystemVersionId(
            UUID systemVersionId,
            int pageNumber,
            int pageSize) {
        logger.trace("pageRisksByNames: name={}, pageNumber={}, pageSize={}", systemVersionId, pageNumber, pageSize);
        var page = riskRepository.findBySystemVersionId(systemVersionId, PageRequest.of(pageNumber, pageSize));
        return this.toRiskWideDtoPage(page);
    }

    @Transactional(readOnly = true)
    public PageOfDto<RiskWideDto> pageAllRisksWide(
            int pageNumber,
            int pageSize) {
        logger.trace("pageAllRisks: pageNumber={}, pageSize={}", pageNumber, pageSize);
        var page = riskRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return this.toRiskWideDtoPage(page);
    }

    private PageOfDto<RiskWideDto> toRiskWideDtoPage(Page<Risk> page) {
        return new PageOfDto<>(
                toRiskWideDtoList(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages());
    }

    public RiskWideDto toRiskWideDto(Risk risk) {
        if (risk == null) {
            return null;
        }

        return new RiskWideDto(
                risk.getId(),
                risk.getName(),
                risk.getDescription(),
                risk.getOwnerName(),
                risk.getOwnerUsername(),
                toRiskProbabilityClassDto(risk.getProbabilityClass()),
                toRiskImpactClassDto(risk.getImpactClass()));
    }

    private ProbabilityClassDto toRiskProbabilityClassDto(ProbabilityClass probabilityClass) {
        if (probabilityClass == null) {
            return null;
        }

        return new ProbabilityClassDto(
                probabilityClass.getId(),
                probabilityClass.getCode(),
                probabilityClass.getName(),
                probabilityClass.getDescription()
        );
    }

    private ImpactClassDto toRiskImpactClassDto(ImpactClass impactClass) {
        if (impactClass == null) {
            return null;
        }

        return new ImpactClassDto(
                impactClass.getId(),
                impactClass.getCode(),
                impactClass.getName(),
                impactClass.getDescription()
        );
    }

    public List<RiskWideDto> toRiskWideDtoList(List<Risk> riskList) {
        return riskList.stream().map(this::toRiskWideDto).toList();
    }
}