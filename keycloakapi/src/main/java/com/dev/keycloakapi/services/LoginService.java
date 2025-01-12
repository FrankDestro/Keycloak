package com.dev.keycloakapi.services;

import com.dev.keycloakapi.models.User;
import org.springframework.http.ResponseEntity;

public interface LoginService<T> {

    ResponseEntity<T> login(User user);
    ResponseEntity<T> refreshToken(String refreshToken);

}