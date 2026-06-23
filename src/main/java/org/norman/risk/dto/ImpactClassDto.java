package org.norman.risk.dto;

import org.norman.risk.entity.ImpactClass;

import java.util.UUID;

public record ImpactClassDto(
        UUID id,
        String code,
        String name,
        String description
) {
    public static ImpactClassDto from(ImpactClass impactClass) {
        return impactClass == null ? null : new ImpactClassDto(
                impactClass.getId(),
                impactClass.getCode(),
                impactClass.getName(),
                impactClass.getDescription());
    }
}
