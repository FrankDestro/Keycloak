package com.dev.keycloakapi.services.implementations;

import com.dev.keycloakapi.component.HttpComponent;
import com.dev.keycloakapi.models.User;
import com.dev.keycloakapi.services.LoginService;
import com.dev.keycloakapi.utils.HttpParamsMapBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;


@Service
@RequiredArgsConstructor
public class LoginServiceKeycloakImpl implements LoginService<String> {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    @Value("${keycloak.user-login.grant-type}")
    private String grantType;


    private final HttpComponent httpComponent;


    @Override
    public ResponseEntity<String> login(User user) {
        httpComponent.httpHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = HttpParamsMapBuilder.builder()
                .withClient(clientId)
                .withClientSecret(clientSecret)
                .withGrantType(grantType)
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .build();

        System.out.println(map);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpComponent.httpHeaders());

        try {
            System.out.println(keycloakServerUrl+"/protocol/openid-connect/token");
            System.out.println("http://localhost:8080/realms/REALM_SPRING_API/protocol/openid-connect/token");

            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                    keycloakServerUrl + "/protocol/openid-connect/token",
                    request,
                    String.class
            );
            System.out.println(keycloakServerUrl+"/protocol/openid-connect/token");
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> refreshToken(String refreshToken) {
        httpComponent.httpHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> map = HttpParamsMapBuilder.builder()
                .withClient(clientId)
                .withClientSecret(clientSecret)
                .withGrantType("refresh_token")
                .withRefreshToken(refreshToken)
                .build();

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, httpComponent.httpHeaders());

        try{
            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                    keycloakServerUrl+"/protocol/openid-connect/token",
                    request,
                    String.class
            );
            return ResponseEntity.ok(response.getBody());
        }catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }

    }
}

