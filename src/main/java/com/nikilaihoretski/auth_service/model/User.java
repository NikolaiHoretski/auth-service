package com.nikilaihoretski.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.nikilaihoretski.auth_service.util.UnitDictionary.ID_GENERATOR_SUFFIX;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    public static final String ENTITY_NAME = "User";
    public static final String ENTITY_GENERATOR_NAME = ENTITY_NAME + ID_GENERATOR_SUFFIX;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GENERATOR_NAME)
    @SequenceGenerator(name = ENTITY_GENERATOR_NAME, sequenceName = "USER_SEQ", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


}
