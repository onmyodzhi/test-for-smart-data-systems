package com.SmartDataSystems.test.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Getter
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeycloakConfig {

    @Value("${KEYCLOAK_CLIENT_ID}")
    String clientId;

    @Value("${KEYCLOAK_GRANT_TYPE}")
    String grantType;

    String resourceServerUrl;

    @Bean
    @Profile("dev")
    public KeycloakConfigDev keycloakConfigDev(@Value("${KEYCLOAK_RESOURCE_SERVER_URL}") String resourceServerUrl) {
        this.resourceServerUrl = resourceServerUrl;
        return new KeycloakConfigDev(clientId, resourceServerUrl, grantType);
    }

    @Bean
    @Profile("prod")
    public KeycloakConfigProd keycloakConfigProd(@Value("${PROD_KEYCLOAK_RESOURCE_SERVER_URL}") String resourceServerUrl) {
        this.resourceServerUrl = resourceServerUrl;
        return new KeycloakConfigProd(clientId, resourceServerUrl, grantType);
    }
}
