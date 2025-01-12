package com.dev.keycloakapi.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    private final JwtConverter jwtConverter;
    private final JwtDecoderConfig jwtDecoderConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login/**").permitAll();
                    auth.requestMatchers("/auth/refresh/**").permitAll();
                    auth.requestMatchers("/resources/admin/**").hasAuthority("ROLE_ADMIN");
                    auth.requestMatchers("/resources/operation/**").hasAuthority("ROLE_OPERATION");
                })
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoderConfig.jwtDecoder())
                                .jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
                );
        return httpSecurity.build();
    }
}
