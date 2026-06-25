package org.norman.system.dto;

import org.norman.system.entity.RiskArea;

import java.util.List;
import java.util.UUID;

public record RiskAreaDto(
        UUID id,
        String code,
        String name,
        String description) {
    public static RiskAreaDto from(RiskArea riskArea) {
        return riskArea == null ? null : new RiskAreaDto(
                riskArea.getId(),
                riskArea.getCode(),
                riskArea.getName(),
                riskArea.getDescription());
    }

    public static List<RiskAreaDto> fromList(List<RiskArea> riskAreas) {
        return riskAreas == null ? null : riskAreas
                .stream()
                .map(RiskAreaDto::from)
                .toList();
    }
}