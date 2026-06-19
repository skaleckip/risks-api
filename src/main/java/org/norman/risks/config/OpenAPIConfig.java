package org.norman.risks.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    private final OAuth2ResourceServerProperties properties;

    public OpenAPIConfig(OAuth2ResourceServerProperties properties) {
        this.properties = properties;
    }

    @Bean
    OpenAPI openAPI() {
        var issuer = properties.getJwt().getIssuerUri();
        return new OpenAPI()
                .info(new Info().title("Secured API").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes("Norman", new SecurityScheme()
                                .name("Norman")
                                .type(SecurityScheme.Type.OPENIDCONNECT)
                                .openIdConnectUrl(issuer + "/.well-known/openid-configuration")));
    }
}
