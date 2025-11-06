package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import static com.nikilaihoretski.auth_service.util.UnitDictionary.ID_GENERATOR_SUFFIX;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {

    public static final String ENTITY_NAME = "Permission";
    public static final String ENTITY_GENERATOR_NAME = ENTITY_NAME + ID_GENERATOR_SUFFIX;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GENERATOR_NAME)
    @SequenceGenerator(name = ENTITY_GENERATOR_NAME, sequenceName = "PERM_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission_name", nullable = false, unique = true)
    private String name;
}
