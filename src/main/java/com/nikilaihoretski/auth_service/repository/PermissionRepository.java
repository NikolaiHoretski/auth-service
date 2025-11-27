package com.nikilaihoretski.auth_service.repository;

import com.nikilaihoretski.auth_service.model.Permission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByNameIn(Collection<String> names);


}
