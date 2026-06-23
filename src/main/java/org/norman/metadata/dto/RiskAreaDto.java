package org.norman.metadata.dto;

import org.norman.metadata.entity.RiskArea;

import java.util.List;
import java.util.UUID;

public record RiskAreaDto(
        UUID id,
        String code) {
    public static RiskAreaDto from(RiskArea riskArea) {
        return riskArea == null ? null : new RiskAreaDto(
                riskArea.getId(),
                riskArea.getCode());
    }

    public static List<RiskAreaDto> fromList(List<RiskArea> riskAreas) {
        return riskAreas == null ? null : riskAreas
                .stream()
                .map(RiskAreaDto::from)
                .toList();
    }
}