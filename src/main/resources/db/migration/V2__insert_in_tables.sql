INSERT INTO permissions (permission_name) VALUES ('READ_PRIVILEGE');
INSERT INTO permissions (permission_name) VALUES ('WRITE_PRIVILEGE');
INSERT INTO permissions (permission_name) VALUES ('DELETE_PRIVILEGE');
INSERT INTO permissions (permission_name) VALUES ('EXECUTE_PRIVILEGE');

INSERT INTO roles (role_name) VALUES ('ADMIN');
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('MANAGER');

-- ADMIN имеет все разрешения
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 1);
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 2);
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 3);
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 4);

-- USER имеет только READ и EXECUTE
INSERT INTO roles_permissions (role_id, permission_id) VALUES (2, 1);
INSERT INTO roles_permissions (role_id, permission_id) VALUES (2, 4);

-- MANAGER имеет READ и WRITE
INSERT INTO roles_permissions (role_id, permission_id) VALUES (3, 1);
INSERT INTO roles_permissions (role_id, permission_id) VALUES (3, 2);


INSERT INTO users (username, password, email) VALUES ('admin', '$2a$12$A0ipvdaWDrp0ERJktlzOTuEcCG2XN/NOXoFzzA9NZTzw/4FUuAqdq', 'alice@example.com');
INSERT INTO users (username, password, email) VALUES ('user', '$2a$12$LU2RRFz7fzCYOP.8l2c17.Q9Hy7m9NKhlDNkEofn5bQYeW2jmABsS', 'bob@example.com');
INSERT INTO users (username, password, email) VALUES ('manager', '$2a$12$z94zMqS3RT62hxxTfmksmureuruP02h0tQMYanwScvV2TePTj1WxS', 'carol@example.com');

