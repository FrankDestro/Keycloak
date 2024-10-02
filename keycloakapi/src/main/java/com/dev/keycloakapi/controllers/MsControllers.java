package com.dev.keycloakapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resources")
public class MsControllers {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminAccess(){
        return "Acesso concedido ao ADMIN";
    }

    @GetMapping("/operation")
    @PreAuthorize("hasAuthority('ROLE_OPERATION')")
    public String operationAccess(){
        return "Acesso concedido à OPERAÇÃO";
    }

}

