package org.norman.system.dto;

import org.norman.system.entity.ImpactClass;

import java.util.List;
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
                impactClass.getDescription()
        );
    }

    public static List<ImpactClassDto> fromList(List<ImpactClass> impactClasses) {
        return impactClasses == null ? null : impactClasses
                .stream()
                .map(ImpactClassDto::from)
                .toList();
    }
}
