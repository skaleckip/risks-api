package org.norman.system.dto;

import org.norman.system.entity.ProbabilityClass;

import java.util.List;
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

    public static List<ProbabilityClassDto> fromList(List<ProbabilityClass> probabilityClasses) {
        return probabilityClasses == null ? null : probabilityClasses
                .stream()
                .map(ProbabilityClassDto::from)
                .toList();
    }
}
