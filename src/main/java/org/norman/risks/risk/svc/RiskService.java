package org.norman.risks.risk.svc;

import jakarta.transaction.Transactional;
import org.norman.risks.metadata.model.RiskArea;
import org.norman.risks.risk.dto.RiskDto;
import org.norman.risks.risk.model.Risk;
import org.norman.risks.risk.model.RiskRepository;
import org.norman.risks.shared.dto.PageOfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    @Transactional
    public Optional<RiskDto> findRiskByIdOptional(UUID id) {
        logger.trace("findRiskByIdOptional: id={}", id);
        return riskRepository.findById(id).map(this::toRiskDto);
    }

    @Transactional
    public PageOfDto<RiskDto> pageRisksByNames(
            String name,
            int pageIndex,
            int pageSize) {
        logger.trace("pageRisksByNames: name={}, pageIndex={}, pageSize={}", name, pageIndex, pageSize);
        var page = riskRepository.findByNameLike(name, PageRequest.of(pageIndex, pageSize));
        return this.toRiskDtoPage(page);
    }

    @Transactional
    public PageOfDto<RiskDto> pageAllRisks(
            int pageIndex,
            int pageSize) {
        logger.trace("pageAllRisks: pageIndex={}, pageSize={}", pageIndex, pageSize);
        var page = riskRepository.findAll(PageRequest.of(pageIndex, pageSize));
        return this.toRiskDtoPage(page);
    }

    public RiskDto toRiskDto(Risk risk) {
        if (risk == null) {
            return null;
        }
        
        return new RiskDto(
                risk.getId(),
                risk.getName(),
                Optional.ofNullable(risk.getArea()).map(RiskArea::getId).orElse(null));
    }

    public List<RiskDto> toRiskDtoList(List<Risk> riskList) {
        return riskList.stream().map(this::toRiskDto).toList();
    }

    public PageOfDto<RiskDto> toRiskDtoPage(Page<Risk> riskPage) {
        return new PageOfDto<>(this.toRiskDtoList(riskPage.getContent()), riskPage.getNumber() - 1, riskPage.getSize(), riskPage.getTotalPages());
    }
}