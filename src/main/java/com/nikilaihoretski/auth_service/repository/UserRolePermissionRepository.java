package com.nikilaihoretski.auth_service.repository;

import com.nikilaihoretski.auth_service.model.UserRolePermission;
import com.nikilaihoretski.auth_service.model.UserRolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolePermissionRepository extends JpaRepository<UserRolePermission, UserRolePermissionId> {
}
