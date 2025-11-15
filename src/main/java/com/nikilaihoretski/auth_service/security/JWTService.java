package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.dto.UserDtoForCreateJwtToken;
import com.nikilaihoretski.auth_service.dto.UserMapper;
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

@Service
public class JWTService {

    Logger logger = LoggerFactory.getLogger(JWTService.class);

    @Value("${jwt.secretKey}")
    private String jwtSecreteKey;

    public SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(User user) {

        UserDtoForCreateJwtToken dto = UserMapper.toDto(user);

        logger.info(user.getUsername());
        logger.info(user.getEmail());

        return Jwts.builder()
                .subject(dto.getUsername())
                .claim("email", dto.getEmail())
                .claim("fullname", dto.getFullName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(generateSecretKey())
                .compact();
    }

    public String createRefreshToken(User user) {

        UserDtoForCreateJwtToken dto = UserMapper.toDto(user);

        return Jwts.builder()
                .subject(dto.getUsername())
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
