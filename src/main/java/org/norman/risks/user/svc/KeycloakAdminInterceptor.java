package org.norman.risks.user.svc;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KeycloakAdminInterceptor implements ClientHttpRequestInterceptor {
    // here you have to replace registrationId with the one that you
    // used in the application.properties
    private static final String REGISTRATION_ID = "keycloak-admin-api";

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public KeycloakAdminInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @NonNull
    @Override
    public ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            byte @NonNull [] body,
            @NonNull ClientHttpRequestExecution execution) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            // log an error, it can be helpful during debugging
            return execution.execute(request, body);
        }
        OAuth2AuthorizeRequest req = OAuth2AuthorizeRequest.withClientRegistrationId(REGISTRATION_ID).principal(authentication.getName()).build();
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(req);
        if (authorizedClient == null) {
            // log an error, it can be helpful during debugging
            return execution.execute(request, body);
        }
        // here we're actually using the OAuth2 client that we configured before
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        // this is the most important line, where we set the bearer token value
        request.getHeaders().setBearerAuth(accessToken.getTokenValue());
        return execution.execute(request, body);
    }
}
