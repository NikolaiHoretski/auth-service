package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRolePermissionId implements Serializable {

    private Long userId;
    private Long roleId;
    private Long permissionId;
}
