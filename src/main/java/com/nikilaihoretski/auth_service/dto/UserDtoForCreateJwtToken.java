package com.nikilaihoretski.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForCreateJwtToken {

    private String username;
    private String email;
    private String fullName;


}
