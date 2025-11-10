package com.nikilaihoretski.auth_service.service;

import com.nikilaihoretski.auth_service.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserByUsername(String username);
}
