package org.norman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
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
        return roles.stream()
                // The hasRole(...) Spring method required ROLE_ prefix
                // We could use hasAutority(...), but let's make it Spring-native.
                .map(role -> String.format("ROLE_%s", role))
                .map(role -> ((GrantedAuthority) new SimpleGrantedAuthority(role)))
                .toList();
    }

    @SuppressWarnings("RedundantThrows")
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        //noinspection Convert2MethodRef
        return http
                .cors(_ -> corsConfigurationSource())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs*/**").permitAll()
                        .requestMatchers("/api/users").hasRole("auditor")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()))
                .build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        // Todo, add externalized configuration for all the CORS settings
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://127.0.0.1:5173",
                "http://localhost:3000",
                "http://127.0.0.1:3000",
                "http://localhost:9090",
                "http://127.0.0.1:9090"
        ));
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.HEAD.name(),
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
