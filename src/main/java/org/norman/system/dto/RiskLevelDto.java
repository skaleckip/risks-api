package org.norman.system.dto;

import org.norman.system.entity.RiskLevel;

import java.util.List;
import java.util.UUID;

public record RiskLevelDto(
        UUID id,
        ProbabilityClassDto probabilityClass,
        ImpactClassDto impactClass,
        Short level
) {
    public static RiskLevelDto from(RiskLevel riskLevel) {
        return riskLevel == null ? null : new RiskLevelDto(
                riskLevel.getId(),
                ProbabilityClassDto.from(riskLevel.getProbabilityClass()),
                ImpactClassDto.from(riskLevel.getImpactClass()),
                riskLevel.getLevel()
        );
    }

    public static List<RiskLevelDto> fromList(List<RiskLevel> riskLevels) {
        return riskLevels == null ? null : riskLevels
                .stream()
                .map(RiskLevelDto::from)
                .toList();
    }
}
