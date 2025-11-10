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
                authority.add(new SimpleGrantedAuthority(Permissions.MANAGE_PRODUCTS_CATALOG.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.ADD_PRODUCTS_TO_CART.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.MANAGE_USERS.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.CREATE_ORDER.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.MANAGE_INVENTORY_WAREHOUSE.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.VIEW_ALL_ORDERS.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.VIEW_PRODUCTS.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.VIEW_OWN_ORDERS.toString()));
                authority.add(new SimpleGrantedAuthority(Permissions.UPDATE_ORDER_STATUS.toString()));
            } else {
                role.getPermissions().forEach(permission -> authority.add(new SimpleGrantedAuthority((permission.getName()))));
            }
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
