package com.nikilaihoretski.auth_service.service;

import com.nikilaihoretski.auth_service.dto.UserDtoForJwtToken;
import com.nikilaihoretski.auth_service.model.RegisterRequest;
import com.nikilaihoretski.auth_service.model.User;
import com.nikilaihoretski.auth_service.repository.UserRepository;
import com.nikilaihoretski.auth_service.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService service;

    public String verify(RegisterRequest registerRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(registerRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));

        UserDtoForJwtToken dto = new UserDtoForJwtToken();
        if(authentication.isAuthenticated()) {
            return service.generateUserIdFromToken(registerRequest.getUsername());
        }

        return "verify is failed";
    }
}
