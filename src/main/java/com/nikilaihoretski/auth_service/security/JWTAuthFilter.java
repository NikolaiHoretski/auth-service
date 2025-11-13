package com.nikilaihoretski.auth_service.security;

import com.nikilaihoretski.auth_service.repository.UserRepository;
import com.nikilaihoretski.auth_service.service.CustomUserDetailService;
import com.nikilaihoretski.auth_service.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserServiceImpl userService;
    private final ApplicationContext context;

    private final UserRepository userRepository;

    public JWTAuthFilter(JWTService jwtService, UserServiceImpl userService, ApplicationContext context,
                         UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.context = context;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.generateUserIdFromToken(token);
            } catch (Exception e) {
                logger.error("Failed to extract username from token", e);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                CustomUserDetailService userDetailService = context.getBean(CustomUserDetailService.class);
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (UsernameNotFoundException e) {
                logger.warn("User not found for id: {}");
            } catch (NumberFormatException e) {
                logger.error("Invalid userId format: {}");
            }
            }
            filterChain.doFilter(request, response);
        }
    }
