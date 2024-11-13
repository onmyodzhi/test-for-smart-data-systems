package com.SmartDataSystems.test.controllers.impls;

import com.SmartDataSystems.test.configs.KeycloakConfig;
import com.SmartDataSystems.test.controllers.AuthController;
import com.SmartDataSystems.test.models.DTOs.AuthDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthControllerImpl implements AuthController {

    KeycloakConfig keycloakConfig;

    @Override
    public String auth(@RequestBody AuthDto authDto) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = "client_id=" + keycloakConfig.getClientId() +
                "&username=" + authDto.getLogin() +
                "&password=" + authDto.getPassword() +
                "&grant_type=" + keycloakConfig.getGrantType();

        var requestEntity = new HttpEntity<>(body, headers);

        var response = new RestTemplate()
                .exchange(
                        keycloakConfig.getResourceServerUrl(),
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );

        if (response.getStatusCode().value() == 200) {
            return response.getBody();
        }

        return null;
    }
}
