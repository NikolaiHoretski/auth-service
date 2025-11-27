package com.nikilaihoretski.auth_service.controller;

import com.nikilaihoretski.auth_service.dto.RegisterRequest;
import com.nikilaihoretski.auth_service.model.User;
import com.nikilaihoretski.auth_service.service.RegistrationAuthenticationService;
import com.nikilaihoretski.auth_service.service.UserService;
import com.nikilaihoretski.auth_service.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RegistrationAuthenticationService registration;

    public AdminController(UserServiceImpl userService, RegistrationAuthenticationService registration) {
        this.userService = userService;
        this.registration = registration;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/{username}")
    public User updateUser(@PathVariable String username, Map<String, Object> updates) {
        return userService.updateUserField(username, updates);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> addUserByAdmin(@RequestBody RegisterRequest registerRequest) {
        try {
            registration.register(registerRequest);
            return ResponseEntity.ok(registerRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
