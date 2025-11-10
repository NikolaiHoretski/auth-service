--INSERT INTO permissions (permission_name) VALUES ('VIEW_PRODUCTS');
--INSERT INTO permissions (permission_name) VALUES ('ADD_PRODUCTS_TO_CART');
--INSERT INTO permissions (permission_name) VALUES ('CREATE_ORDER');
--INSERT INTO permissions (permission_name) VALUES ('VIEW_OWN_ORDERS');
--INSERT INTO permissions (permission_name) VALUES ('VIEW_ALL_ORDERS');
--INSERT INTO permissions (permission_name) VALUES ('UPDATE_ORDER_STATUS');
--INSERT INTO permissions (permission_name) VALUES ('MANAGE_INVENTORY_WAREHOUSE');
--INSERT INTO permissions (permission_name) VALUES ('MANAGE_USERS');
--INSERT INTO permissions (permission_name) VALUES ('MANAGE_PRODUCTS_CATALOG');

INSERT INTO permissions (permission_name) VALUES
('VIEW_PRODUCTS'),
('ADD_PRODUCTS_TO_CART'),
('CREATE_ORDER'),
('VIEW_OWN_ORDERS'),
('VIEW_ALL_ORDERS'),
('UPDATE_ORDER_STATUS'),
('MANAGE_INVENTORY_WAREHOUSE'),
('MANAGE_USERS'),
('MANAGE_PRODUCTS_CATALOG');

--INSERT INTO roles (role_name) VALUES ('ADMIN');
--INSERT INTO roles (role_name) VALUES ('GUEST');
--INSERT INTO roles (role_name) VALUES ('CUSTOMER');
--INSERT INTO roles (role_name) VALUES ('MANAGER');
--INSERT INTO roles (role_name) VALUES ('WAREHOUSE');

INSERT INTO roles (role_name) VALUES
('ADMIN'),
('GUEST'),
('CUSTOMER'),
('MANAGER'),
('WAREHOUSE');

INSERT INTO roles_permissions (role_id, permission_id)
SELECT 1, id FROM permissions;

INSERT INTO roles_permissions (role_id, permission_id)
SELECT 2, id FROM permissions WHERE permission_name = 'VIEW_PRODUCTS';

INSERT INTO users (username, password, email) VALUES
('admin', '$2a$12$HxsLYYIrUuEVegg90Vgb8eWzKMOQ91fxyOlTJ2bVhWQxjujPtkskq', 'admin@example.com'),
('guest', '$2a$12$j3wFFvnrO7TRxba91Bu1duIp0UdIjN9mVoiK3LWq1Sjmi6Po0R98.', 'guest@example.com'),
('manager', '$2a$12$Jh8RKx4YnATCqeLn6iqGb.qZI245SP2cl8lBSFf7yhZ0doJOaHdhK', 'manager@example.com'),
('customer', '$2a$12$.TKTMyFH.sv2OVs.fNAM6.di7jPL23thrsSKm7WtvZXpBReKgTw8.', 'customer@example.com'),
('warehouse', '$2a$12$I3geFmDqY9/xdhNA9CNhcO85xoCYbiRDZaQ.xGMCZwqZMDsXZb.vG', 'warehouse@example.com');


INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),  -- admin -> ADMIN
(2, 2),  -- guest -> GUEST
(3, 4),  -- manager -> MANAGER
(4, 3),  -- customer -> CUSTOMER
(5, 5);  -- warehouse -> WAREHOUSE

--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 1);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 2);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 3);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 4);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 5);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 6);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 7);
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 8);
--
--INSERT INTO roles_permissions (role_id, permission_id) VALUES (2, 1);
--
--INSERT INTO users (username, password, email) VALUES ('admin', '$2a$12$HxsLYYIrUuEVegg90Vgb8eWzKMOQ91fxyOlTJ2bVhWQxjujPtkskq', 'admin@example.com');
--INSERT INTO users (username, password, email) VALUES ('guest', '$2a$12$j3wFFvnrO7TRxba91Bu1duIp0UdIjN9mVoiK3LWq1Sjmi6Po0R98.', 'guest@example.com');
--INSERT INTO users (username, password, email) VALUES ('manager', '$2a$12$Jh8RKx4YnATCqeLn6iqGb.qZI245SP2cl8lBSFf7yhZ0doJOaHdhK', 'manager@example.com');
--INSERT INTO users (username, password, email) VALUES ('customer', '$2a$12$.TKTMyFH.sv2OVs.fNAM6.di7jPL23thrsSKm7WtvZXpBReKgTw8.', 'customer@example.com');
--INSERT INTO users (username, password, email) VALUES ('warehouse', '$2a$12$YKWwMrKNPg2THqFI6yTazelQH8i1wUaYIH12OYm1qwsjcBOI3iy/C', 'warehouse@example.com');

