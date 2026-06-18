package org.norman.risks.risk.dto;

import org.norman.risks.metadata.dto.RiskAreaDto;

public record RiskWideDto(
        String name,
        RiskAreaDto area) {
}