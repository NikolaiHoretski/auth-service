INSERT INTO permissions (permission_name)
VALUES ('VIEW_PRODUCTS'),
       ('ADD_PRODUCTS_TO_CART'),
       ('CREATE_ORDER'),
       ('VIEW_OWN_ORDERS'),
       ('VIEW_ALL_ORDERS'),
       ('UPDATE_ORDER_STATUS'),
       ('MANAGE_INVENTORY_WAREHOUSE'),
       ('MANAGE_USERS'),
       ('MANAGE_PRODUCTS_CATALOG');

INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('GUEST'),
       ('CUSTOMER'),
       ('MANAGER'),
       ('WAREHOUSE');

INSERT INTO users (username, fullname, password, email)
VALUES ('admin', 'admin', '$2a$12$HxsLYYIrUuEVegg90Vgb8eWzKMOQ91fxyOlTJ2bVhWQxjujPtkskq', 'admin@example.com'),
       ('guest', 'guest', '$2a$12$j3wFFvnrO7TRxba91Bu1duIp0UdIjN9mVoiK3LWq1Sjmi6Po0R98.', 'guest@example.com'),
       ('manager', 'manager', '$2a$12$Jh8RKx4YnATCqeLn6iqGb.qZI245SP2cl8lBSFf7yhZ0doJOaHdhK', 'manager@example.com'),
       ('customer', 'customer', '$2a$12$.TKTMyFH.sv2OVs.fNAM6.di7jPL23thrsSKm7WtvZXpBReKgTw8.',
        'customer@example.com'),
       ('warehouse', 'warehouse', '$2a$12$I3geFmDqY9/xdhNA9CNhcO85xoCYbiRDZaQ.xGMCZwqZMDsXZb.vG',
        'warehouse@example.com');


INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'VIEW_PRODUCTS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'ADD_PRODUCTS_TO_CART'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'CREATE_ORDER'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'VIEW_OWN_ORDERS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'VIEW_ALL_ORDERS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'UPDATE_ORDER_STATUS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'MANAGE_INVENTORY_WAREHOUSE'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'MANAGE_USERS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE role_name = 'ADMIN'),
        (SELECT id FROM permissions WHERE permission_name = 'MANAGE_PRODUCTS_CATALOG'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'manager'),
        (SELECT id FROM roles WHERE role_name = 'MANAGER'),
        (SELECT id FROM permissions WHERE permission_name = 'MANAGE_USERS'));

INSERT INTO users_roles_permissions (user_id, role_id, permission_id)
VALUES ((SELECT id FROM users WHERE username = 'manager'),
        (SELECT id FROM roles WHERE role_name = 'MANAGER'),
        (SELECT id FROM permissions WHERE permission_name = 'MANAGE_PRODUCTS_CATALOG'));