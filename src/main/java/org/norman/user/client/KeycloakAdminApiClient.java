package org.norman.user.client;

import org.norman.user.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Component
public class KeycloakAdminApiClient {
    private final String clientRegistrationId = "keycloak-admin-api";
    private final RestClient restClient;

    public KeycloakAdminApiClient(@Qualifier("keycloakAdminApiRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public List<User> getUsers() {
        var users = restClient.get()
                .uri("/users")
                .attributes(clientRegistrationId(clientRegistrationId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {
                });
        if (users == null) {
            return Collections.emptyList();
        }
        return users;
    }

    @SuppressWarnings("unused")
    public User getUserById(Integer id) {
        return restClient.get()
                .uri("/users/{id}", id)
                .attributes(clientRegistrationId(clientRegistrationId))
                .retrieve()
                .body(User.class);
    }
}
