package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CustomUserDetails implements UserDetails {

    private static final String ROLE = "ROLE_";

    Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        for(UserRolePermission urp : user.getUserRolePermission()) {
            authorities.add(new SimpleGrantedAuthority(ROLE + urp.getRole().getName()));
            authorities.add(new SimpleGrantedAuthority(urp.getPermission().getName()));
        }

        logger.info("Total authorities for {}: {}", user.getUsername(), authorities);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
