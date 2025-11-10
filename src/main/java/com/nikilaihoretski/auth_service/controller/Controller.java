package com.nikilaihoretski.auth_service.controller;

import com.nikilaihoretski.auth_service.model.RegisterRequest;
import com.nikilaihoretski.auth_service.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final AuthenticationService service;

    public Controller(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest request) {
        return service.verify(request);
    }
}
