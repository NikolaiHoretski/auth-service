package com.nikilaihoretski.auth_service.controller;

import com.nikilaihoretski.auth_service.dto.RegisterRequest;
import com.nikilaihoretski.auth_service.service.RegistrationAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final RegistrationAuthenticationService service;

    public LoginController(RegistrationAuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody RegisterRequest request) throws IllegalAccessException {
        Map<String, String> tokens = service.verify(request);
        return ResponseEntity.ok(tokens);
    }
}
