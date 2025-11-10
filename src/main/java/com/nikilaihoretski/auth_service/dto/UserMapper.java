package com.nikilaihoretski.auth_service.dto;
import com.nikilaihoretski.auth_service.model.Role;
import com.nikilaihoretski.auth_service.model.User;


public class UserMapper {

    public static UserDtoForJwtToken toDto(User user, Role role) {

        if (user == null) return null;

        UserDtoForJwtToken dto = new UserDtoForJwtToken();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        dto.setPermissions(role.getPermissions());


        return dto;
    }
}
