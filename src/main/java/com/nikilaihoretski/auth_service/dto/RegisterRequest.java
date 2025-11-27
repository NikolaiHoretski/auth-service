package com.nikilaihoretski.auth_service.dto;

import com.nikilaihoretski.auth_service.model.Permission;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterRequest {

    private String username;
    private String fullname;
    private String password;
    private String email;
    private String role;
    private Set<String> permissions;
}
