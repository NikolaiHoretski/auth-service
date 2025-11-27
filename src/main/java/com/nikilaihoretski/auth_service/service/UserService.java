package com.nikilaihoretski.auth_service.service;

import com.nikilaihoretski.auth_service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    User getUserByUsername(String username);


    List<User> getAllUsers();

    User updateUserField(String username, Map<String, Object> updates);

    void delete(String name);


}
