package com.nikilaihoretski.auth_service.service;

import com.nikilaihoretski.auth_service.dto.RegisterRequest;
import com.nikilaihoretski.auth_service.model.Permission;
import com.nikilaihoretski.auth_service.model.Role;
import com.nikilaihoretski.auth_service.model.User;
import com.nikilaihoretski.auth_service.repository.PermissionRepository;
import com.nikilaihoretski.auth_service.repository.RoleRepository;
import com.nikilaihoretski.auth_service.repository.UserRepository;
import com.nikilaihoretski.auth_service.security.JWTService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RegistrationAuthenticationService {

    Logger logger = LoggerFactory.getLogger(RegistrationAuthenticationService.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final JWTService service;
    private final PasswordEncoder passwordEncoder;

    public RegistrationAuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, JWTService service, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("User already exist");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFullName(registerRequest.getFullname());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setEnabled(true);

        Role role = roleRepository.findByName(registerRequest.getRole()).orElseThrow(() ->
                new RuntimeException("Role not found: " + registerRequest.getRole()));

        user.setRole(role);

        Set<String> permNames = registerRequest.getPermissions();
        logger.info("permissions, которые приходят с фронта: {}", permNames);

        Set<Permission> permissions = permissionRepository.findByNameIn(permNames);
        logger.info("permissions, которые приходит из базы: {}", permissions);



        roleRepository.save(role);
        user.setRole(role);
        userRepository.save(user);
    }

    public Map<String, String> verify(RegisterRequest registerRequest) throws IllegalAccessException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        }

        User user = userRepository.findByUsername(registerRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));


        String accessToken = service.createAccessToken(user);
        String refreshToken = service.createRefreshToken(user);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}
