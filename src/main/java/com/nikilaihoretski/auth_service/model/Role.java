package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.nikilaihoretski.auth_service.util.UnitDictionary.ID_GENERATOR_SUFFIX;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    public static final String ENTITY_NAME = "Role";
    public static final String ENTITY_GENERATOR_NAME = ENTITY_NAME + ID_GENERATOR_SUFFIX;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GENERATOR_NAME)
    @SequenceGenerator(name = ENTITY_GENERATOR_NAME, sequenceName = "ROLE_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id")
    )
    private Set<Permission> permissions = new HashSet<>();

}
