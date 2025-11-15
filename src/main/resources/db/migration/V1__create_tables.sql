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
    fullname VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    enabled BOOLEAN DEFAULT TRUE,
    role_id BIGINT NOT NULL,
    CONSTRAINT uq_username UNIQUE (username),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id)
        REFERENCES roles(id)
);