package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_roles_permissions",
                uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id", "permission_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRolePermission {

    @EmbeddedId
    private UserRolePermissionId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

}
