package org.norman.risks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class RisksApiApp {
    @SuppressWarnings("UnnecessaryModifier")
    public static void main(String[] args) {
        SpringApplication.run(RisksApiApp.class, args);
    }
}
