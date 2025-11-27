package com.nikilaihoretski.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @PreAuthorize("hasAuthority(T(com.nikilaihoretski.auth_service.model.Permissions).MANAGE_USERS.getAuthority()) and" +
    "hasAuthority(T(com.nikilaihoretski.auth_service.model.Permissions).MANAGE_PRODUCTS_CATALOG.getAuthority())"
    )
    @GetMapping("/hello")
    public String helloManager() {
        return "Hello Manager";
    }
}
