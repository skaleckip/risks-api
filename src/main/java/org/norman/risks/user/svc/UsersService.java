package org.norman.risks.user.svc;

import org.norman.risks.user.dto.UserDto;
import org.norman.risks.user.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.TimeZone;

@Service
public class UsersService {
    private final KeycloakAdminClient keycloakAdminClient;

    public UsersService(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public List<UserDto> listUsers() {
        return keycloakAdminClient.getUsers().stream().map(this::toUserDto).toList();
    }

    private UserDto toUserDto(User user) {
        return new UserDto(
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
}
