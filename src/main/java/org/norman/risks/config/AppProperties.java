package org.norman.risks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("SpellCheckingInspection")
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Keycloak keycloak;
    private AdminApi adminApi;
    private OpenApi openApi;

    public AppProperties(Keycloak keycloak, AdminApi adminApi, OpenApi openApi) {
        this.keycloak = keycloak;
        this.adminApi = adminApi;
        this.openApi = openApi;
    }

    public static class Keycloak {
        private String url;
        private String realm;

        public Keycloak(String url, String realm) {
            this.url = url;
            this.realm = realm;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRealm() {
            return realm;
        }

        public void setRealm(String realm) {
            this.realm = realm;
        }
    }

    public static class AdminApi {
        private String clientId;
        private String clientSecret;

        public AdminApi(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    public static class OpenApi {
        private String clientId;
        private String clientSecret;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
}
