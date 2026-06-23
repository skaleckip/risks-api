package org.norman.risks.user.svc;

import org.norman.risks.config.AppProperties;
import org.norman.risks.user.model.User;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class KeycloakAdminClient {
    private final RestClient restClient;

    public KeycloakAdminClient(
            RestClient.Builder builder,
            OAuth2ResourceServerProperties authProperties,
            KeycloakAdminInterceptor interceptor,
            AppProperties appProperties) {
        Objects.requireNonNull(authProperties.getJwt());
        Objects.requireNonNull(authProperties.getJwt().getIssuerUri());
        Objects.requireNonNull(appProperties.getKeycloak());
        Objects.requireNonNull(appProperties.getKeycloak().getUrl());
        Objects.requireNonNull(appProperties.getKeycloak().getRealm());

        var baseUrl = MessageFormat.format("{0}/admin/realms/{1}",
                appProperties.getKeycloak().getUrl(),
                appProperties.getKeycloak().getRealm());

        this.restClient = builder
                .requestInterceptor(interceptor)
                .baseUrl(baseUrl)
                .build();
    }

    public List<User> getUsers() {
        var users = restClient.get()
                .uri("/users")
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
                .retrieve()
                .body(User.class);
    }
}
