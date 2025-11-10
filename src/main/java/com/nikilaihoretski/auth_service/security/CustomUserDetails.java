package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.model.Permissions;
import com.nikilaihoretski.auth_service.model.Roles;
import com.nikilaihoretski.auth_service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CustomUserDetails implements UserDetails {

    Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authority = new HashSet<>();

        user.getRoles().forEach(role -> {
            authority.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            role.getPermissions().forEach(permission -> authority.add(new SimpleGrantedAuthority(permission.getName())));

            logger.info("permission in class CustomUserDetails: {}", role.getPermissions());
        });

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
