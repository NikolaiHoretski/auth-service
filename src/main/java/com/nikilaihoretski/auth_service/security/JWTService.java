package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.model.Permission;
import com.nikilaihoretski.auth_service.model.Role;
import com.nikilaihoretski.auth_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JWTService {

    Logger logger = LoggerFactory.getLogger(JWTService.class);

    @Value("${jwt.secretKey}")
    private String jwtSecreteKey;

    public SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(User user) {

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream().map(Permission::getName))
                .collect(Collectors.toSet());

        logger.info(permissions.toString());
        logger.info(roles.toString());
        logger.info(user.getUsername());
        logger.info(user.getEmail());


        return Jwts.builder()
                .subject(user.getUsername())
                .claim("email", user.getEmail())
                .claim("roles", roles)
                .claim("permissions", permissions)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(generateSecretKey())
                .compact();
    }

    public String createRefreshToken(User user) {

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 3))
                .signWith(generateSecretKey())
                .compact();
    }

    public String generateUserIdFromToken(String token) throws IllegalAccessException {

        if(token == null || token.isEmpty()) {
            throw  new IllegalAccessException("JWT token is missing");
        }
        Claims claims = Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
