package org.norman.risks.risk.dto;

import java.util.UUID;

public record RiskWideDto(
        UUID id,
        String name,
        String description,
        String ownerName,
        String ownerUsername,
        ProbabilityClassDto probabilityClass,
        ImpactClassDto impactClass) {
}