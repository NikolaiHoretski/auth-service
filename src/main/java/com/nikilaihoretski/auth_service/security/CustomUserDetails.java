package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.model.Permissions;
import com.nikilaihoretski.auth_service.model.Roles;
import com.nikilaihoretski.auth_service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authority = new HashSet<>();

        user.getRoles().forEach(role -> {
            authority.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            if (role.getName().equalsIgnoreCase(Roles.ADMIN.toString())) {
                authority.add(new SimpleGrantedAuthority(Permissions.READ_PRIVILEGE.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.WRITE_PRIVILEGE.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.DELETE_PRIVILEGE.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.EXECUTE_PRIVILEGE.toString()));
            } else {
                role.getPermissions().forEach(permission -> authority.add(new SimpleGrantedAuthority((permission.getName()))));
            }
        });

//        user.getPermissions().forEach(permission ->
//                authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
