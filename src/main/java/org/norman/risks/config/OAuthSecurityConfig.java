package org.norman.risks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@SuppressWarnings("DefaultAnnotationParam")
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class OAuthSecurityConfig {
    // Teach Spring how to read Keycloak-specific data,
    // mainly user roles, from JWT tokens passed in requests.
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> mapAuthorities(jwt.getClaims()));
        return converter;
    }

    // Extract realm roles from JWT token and return them as Spring authorities.
    @SuppressWarnings("unchecked")
    private List<GrantedAuthority> mapAuthorities(final Map<String, Object> attributes) {
        final Map<String, Object> realmAccess = ((Map<String, Object>) attributes
                .getOrDefault("realm_access", Collections.emptyMap()));
        final Collection<String> roles = ((Collection<String>) realmAccess
                .getOrDefault("roles", Collections.emptyList()));
        // Todo, translate roles to ROLE_* authorities to use them with hasRole(...)
        return roles.stream()
                .map(role -> ((GrantedAuthority) new SimpleGrantedAuthority(role)))
                .toList();
    }

    @SuppressWarnings("RedundantThrows")
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        //noinspection Convert2MethodRef
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs*/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()))
                .build();
    }
}
