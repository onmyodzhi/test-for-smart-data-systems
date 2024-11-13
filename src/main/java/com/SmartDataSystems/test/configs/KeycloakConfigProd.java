package com.SmartDataSystems.test.configs;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KeycloakConfigProd {
    String clientId;
    String resourceServerUrl;
    String grantType;

    public KeycloakConfigProd(String clientId, String resourceServerUrl, String grantType) {
        this.clientId = clientId;
        this.resourceServerUrl = resourceServerUrl;
        this.grantType = grantType;
    }
}
