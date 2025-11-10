CREATE SEQUENCE USER_SEQ START 1 INCREMENT 1;
CREATE SEQUENCE ROLE_SEQ START 1 INCREMENT 1;
CREATE SEQUENCE PERM_SEQ START 1 INCREMENT 1;

CREATE TABLE permissions (
    id BIGINT PRIMARY KEY DEFAULT nextval('PERM_SEQ'),
    permission_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE roles (
    id BIGINT PRIMARY KEY DEFAULT nextval('ROLE_SEQ'),
    role_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE roles_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    CONSTRAINT fk_roles_permissions_role FOREIGN KEY (role_id)
        REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT fk_roles_permission FOREIGN KEY (permission_id)
        REFERENCES permissions (id) ON DELETE CASCADE,
    CONSTRAINT fk_roles_permissions PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('USER_SEQ'),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    enabled BOOLEAN DEFAULT TRUE,
    CONSTRAINT uq_username UNIQUE (username)
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id)
        REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id)
);