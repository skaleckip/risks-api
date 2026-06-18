package org.norman.risks.metadata.svc;

import jakarta.transaction.Transactional;
import org.norman.risks.metadata.dto.RiskAreaDto;
import org.norman.risks.metadata.model.RiskArea;
import org.norman.risks.metadata.model.RiskAreaRepository;
import org.norman.risks.shared.dto.PageOfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    @Transactional
    public Optional<RiskAreaDto> findRiskAreaByIdOptional(UUID id) {
        logger.trace("findRiskAreaByIdOptional: id={}", id);
        return riskAreaRepository.findById(id).map(this::toRiskAreaDto);
    }

    @Transactional
    public Optional<RiskAreaDto> findRiskAreaByCodeOptional(String code) {
        logger.trace("findRiskAreaByCodeOptional: code={}", code);
        return riskAreaRepository.findByCode(code).map(this::toRiskAreaDto);
    }

    @Transactional
    public List<RiskAreaDto> listAllRiskAreas() {
        logger.trace("listAllRiskAreas");
        var list = riskAreaRepository.findAll();
        return this.toRiskAreaDtoList(list);
    }

    public RiskAreaDto toRiskAreaDto(RiskArea riskArea) {
        if (riskArea == null) {
            return null;
        }
        
        return new RiskAreaDto(
                riskArea.getId(),
                riskArea.getCode());
    }

    public List<RiskAreaDto> toRiskAreaDtoList(List<RiskArea> riskAreaList) {
        return riskAreaList.stream().map(this::toRiskAreaDto).toList();
    }

    public PageOfDto<RiskAreaDto> toRiskAreaDtoPage(Page<RiskArea> riskAreaPage) {
        return new PageOfDto<>(this.toRiskAreaDtoList(riskAreaPage.getContent()), riskAreaPage.getNumber() - 1, riskAreaPage.getSize(), riskAreaPage.getTotalPages());
    }
}