package org.norman.risk.dto;

import org.norman.risk.entity.Risk;
import org.norman.shared.dto.PageDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public record RiskWideDto(
        UUID id,
        String name,
        String description,
        String ownerName,
        String ownerUsername,
        ProbabilityClassDto probabilityClass,
        ImpactClassDto impactClass) {
    public static RiskWideDto from(Risk risk) {
        return risk == null ? null : new RiskWideDto(
                risk.getId(),
                risk.getName(),
                risk.getDescription(),
                risk.getOwnerName(),
                risk.getOwnerUsername(),
                ProbabilityClassDto.from(risk.getProbabilityClass()),
                ImpactClassDto.from(risk.getImpactClass()));
    }

    public static List<RiskWideDto> fromList(List<Risk> risks) {
        return risks == null ? null : risks
                .stream()
                .map(RiskWideDto::from)
                .toList();
    }

    public static PageDto<RiskWideDto> fromPage(Page<Risk> riskPage) {
        return riskPage == null ? null : new PageDto<>(
                RiskWideDto.fromList(riskPage.getContent()),
                riskPage.getNumber(),
                riskPage.getSize(),
                riskPage.getTotalPages()
        );
    }
}