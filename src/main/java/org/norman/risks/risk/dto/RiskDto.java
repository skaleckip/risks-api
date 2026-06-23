package org.norman.risks.risk.dto;

import java.util.UUID;

public record RiskDto(
        UUID id,
        String name
) {
}