package com.nikilaihoretski.auth_service.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
