package org.norman.risks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    @SuppressWarnings({"unused", "FieldMayBeFinal", "FieldCanBeLocal"})
    private Keycloak keycloak;
    @SuppressWarnings({"unused", "FieldMayBeFinal", "FieldCanBeLocal"})
    private OpenApi openApi;

    public AppProperties(Keycloak keycloak, OpenApi openApi) {
        this.keycloak = keycloak;
        this.openApi = openApi;
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }

    public OpenApi getOpenApi() {
        return openApi;
    }

    public static class Keycloak {
        private String url;
        private String realm;
        private AdminApi adminApi;

        public Keycloak(String url, String realm, AdminApi adminApi) {
            this.url = url;
            this.realm = realm;
            this.adminApi = adminApi;
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

        public AdminApi getAdminApi() {
            return adminApi;
        }

        public void setAdminApi(AdminApi adminApi) {
            this.adminApi = adminApi;
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
