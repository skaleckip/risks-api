package org.norman.risk.dto;

import org.norman.risk.entity.ProbabilityClass;

import java.util.UUID;

public record ProbabilityClassDto(
        UUID id,
        String code,
        String name,
        String description
) {
    public static ProbabilityClassDto from(ProbabilityClass probabilityClass) {
        return probabilityClass == null ? null : new ProbabilityClassDto(
                probabilityClass.getId(),
                probabilityClass.getCode(),
                probabilityClass.getName(),
                probabilityClass.getDescription()
        );
    }
}
