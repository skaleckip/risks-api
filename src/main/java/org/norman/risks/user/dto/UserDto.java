package org.norman.risks.user.dto;

import java.time.LocalTime;

public record UserDto(
        String userName,
        String firstName,
        String lastName,
        String email,
        Boolean emailVerified,
        Boolean enabled,
        LocalTime created
) {
}
