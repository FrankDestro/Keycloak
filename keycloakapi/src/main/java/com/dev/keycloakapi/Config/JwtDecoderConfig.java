package com.dev.keycloakapi.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoderConfig {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(keycloakServerUrl + "/protocol/openid-connect/certs").build();
    }
}
