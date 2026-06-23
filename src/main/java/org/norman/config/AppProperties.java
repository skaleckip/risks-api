package org.norman.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Keycloak keycloak;
    private OpenApi openApi;

    public AppProperties(Keycloak keycloak, OpenApi openApi) {
        this.keycloak = keycloak;
        this.openApi = openApi;
    }

    @Setter
    @Getter
    public static class Keycloak {
        private String url;
        private String realm;
        private AdminApi adminApi;

        public Keycloak(String url, String realm, AdminApi adminApi) {
            this.url = url;
            this.realm = realm;
            this.adminApi = adminApi;
        }
    }

    @Setter
    @Getter
    public static class AdminApi {
        private String clientId;
        private String clientSecret;

        public AdminApi(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }
    }

    @Setter
    @Getter
    public static class OpenApi {
        private String clientId;
        private String clientSecret;

        public OpenApi(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }
    }
}
