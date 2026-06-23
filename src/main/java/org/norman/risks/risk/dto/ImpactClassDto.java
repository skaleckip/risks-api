package org.norman.risks.risk.dto;

import java.util.UUID;

public record ImpactClassDto(
        UUID id,
        String code,
        String name,
        String description
) {
}
