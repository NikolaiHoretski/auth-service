package com.nikilaihoretski.auth_service.dto;

import com.nikilaihoretski.auth_service.model.Permission;
import com.nikilaihoretski.auth_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForJwtToken {

    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Set<Permission> permissions;

}
