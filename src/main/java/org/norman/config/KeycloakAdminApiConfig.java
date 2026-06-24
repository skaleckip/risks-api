package org.norman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

import java.text.MessageFormat;

@Configuration
public class KeycloakAdminApiConfig {
    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean("keycloakAdminApiRestClient")
    public RestClient restClient(
            RestClient.Builder builder,
            OAuth2AuthorizedClientManager authorizedClientManager,
            AppProperties appProperties) {
        var url = appProperties.getKeycloak().getUrl();
        var realm = appProperties.getKeycloak().getRealm();
        var baseUrl = MessageFormat.format("{0}/admin/realms/{1}", url, realm);
        var requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
        return builder
                .baseUrl(baseUrl)
                .requestInterceptor(requestInterceptor)
                .build();
    }
}
