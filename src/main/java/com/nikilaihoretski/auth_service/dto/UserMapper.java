package com.nikilaihoretski.auth_service.dto;

import com.nikilaihoretski.auth_service.model.User;

public class UserMapper {

    public static UserDtoForCreateJwtToken toDto(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Mapping to DTO was not performed");
        }

        UserDtoForCreateJwtToken dto = new UserDtoForCreateJwtToken();

        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());

        return dto;
    }
}
