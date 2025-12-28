package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.nikilaihoretski.auth_service.util.UnitDictionary.ID_GENERATOR_SUFFIX;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "permission")
    private Set<UserRolePermission> userRolePermission = new HashSet<>();
}
