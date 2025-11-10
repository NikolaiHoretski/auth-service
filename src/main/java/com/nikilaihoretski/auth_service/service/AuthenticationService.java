package com.nikilaihoretski.auth_service.service;
import com.nikilaihoretski.auth_service.model.RegisterRequest;
import com.nikilaihoretski.auth_service.model.User;
import com.nikilaihoretski.auth_service.repository.UserRepository;
import com.nikilaihoretski.auth_service.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService service;

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
