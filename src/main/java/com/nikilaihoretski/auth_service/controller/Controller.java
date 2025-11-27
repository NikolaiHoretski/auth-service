package com.nikilaihoretski.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class Controller {

    @PreAuthorize("hasAuthority(T(com.nikilaihoretski.auth_service.model.Permissions).VIEW_PRODUCTS.getAuthority())")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
