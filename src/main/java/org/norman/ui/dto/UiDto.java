package org.norman.ui.dto;

public record UiDto(Boolean showSystemVersions) {
    public static UiDto from(Boolean showSystemVersions) {
        return new UiDto(showSystemVersions);
    }
}
