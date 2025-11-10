package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.dto.UserDtoForJwtToken;
import com.nikilaihoretski.auth_service.dto.UserMapper;
import com.nikilaihoretski.auth_service.model.Role;
import com.nikilaihoretski.auth_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secretKey}")
    private String jwtSecreteKey;

    public SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(User user, Role role) {

        UserDtoForJwtToken dto = UserMapper.toDto(user, role);

        return Jwts.builder()
                .subject(dto.getId().toString())
                .claim("email", dto.getEmail())
                .claim("roles", dto.getRoles().toString())
                .claim("permissions", dto.getPermissions().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(generateSecretKey())
                .compact();
    }

    public String createRefreshToken(User user, Role role) {

        UserDtoForJwtToken dto = UserMapper.toDto(user, role);

        return Jwts.builder()
                .subject(dto.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))
                .signWith(generateSecretKey())
                .compact();
    }

    public String generateUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return String.valueOf(Long.valueOf(claims.getSubject()));
    }
}
