package com.nikilaihoretski.auth_service.controller;

import com.nikilaihoretski.auth_service.dto.RegisterRequest;
import com.nikilaihoretski.auth_service.model.User;
import com.nikilaihoretski.auth_service.service.RegistrationAuthenticationService;
import com.nikilaihoretski.auth_service.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminRegistrationController {

    private final RegistrationAuthenticationService registration;

    public AdminRegistrationController(RegistrationAuthenticationService registration) {
        this.registration = registration;
    }

    @PostMapping("/registration")
   public ResponseEntity<?> registration(@RequestBody RegisterRequest registerRequest) {
        try {
            registration.register(registerRequest);
            return ResponseEntity.ok(registerRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
