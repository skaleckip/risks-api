package org.norman.user.dto;

import org.norman.user.entity.User;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.TimeZone;

public record UserDto(
        String userName,
        String firstName,
        String lastName,
        String email,
        Boolean emailVerified,
        Boolean enabled,
        LocalTime created
) {
    public static UserDto from(User user) {
        return user == null ? null : new UserDto(
                user.username(),
                user.firstName(),
                user.lastName(),
                user.email(),
                user.emailVerified(),
                user.enabled(),
                LocalTime.ofInstant(
                        Instant.ofEpochMilli(user.createdTimestamp()),
                        TimeZone.getDefault().toZoneId())
        );
    }

    public static List<UserDto> fromList(List<User> users) {
        return users == null ? null : users
                .stream()
                .map(UserDto::from)
                .toList();
    }
}
