package org.norman.user.service;

import org.norman.user.client.KeycloakAdminClient;
import org.norman.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final KeycloakAdminClient keycloakAdminClient;

    public UsersService(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public List<UserDto> listUsers() {
        return UserDto.fromList(keycloakAdminClient.getUsers());
    }
}
