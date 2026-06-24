package org.norman.user.service;

import org.norman.user.client.KeycloakAdminApiClient;
import org.norman.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final KeycloakAdminApiClient keycloakAdminApiClient;

    public UsersService(KeycloakAdminApiClient keycloakAdminApiClient) {
        this.keycloakAdminApiClient = keycloakAdminApiClient;
    }

    public List<UserDto> listUsers() {
        return UserDto.fromList(keycloakAdminApiClient.getUsers());
    }
}
