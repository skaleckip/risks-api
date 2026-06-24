package org.norman.system.dto;

import org.norman.system.entity.SystemVersion;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public record SystemVersionDto(
        UUID id,
        String customerName,
        String systemClass,
        String validFrom
) {
    public static SystemVersionDto from(SystemVersion version) {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return version == null ? null : new SystemVersionDto(
                version.getId(),
                version.getCustomer().getName(),
                version.getSystemClass().name(),
                dateFormat.format(version.getValidFrom())
        );
    }

    public static List<SystemVersionDto> fromList(List<SystemVersion> versions) {
        return versions == null ? null : versions
                .stream()
                .map(SystemVersionDto::from)
                .toList();
    }
}
