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

INSERT INTO users (username, fullname, password, email) VALUES
('admin', 'admin', '$2a$12$HxsLYYIrUuEVegg90Vgb8eWzKMOQ91fxyOlTJ2bVhWQxjujPtkskq', 'admin@example.com'),
('guest', 'guest', '$2a$12$j3wFFvnrO7TRxba91Bu1duIp0UdIjN9mVoiK3LWq1Sjmi6Po0R98.', 'guest@example.com'),
('manager', 'manager', '$2a$12$Jh8RKx4YnATCqeLn6iqGb.qZI245SP2cl8lBSFf7yhZ0doJOaHdhK', 'manager@example.com'),
('customer', 'customer', '$2a$12$.TKTMyFH.sv2OVs.fNAM6.di7jPL23thrsSKm7WtvZXpBReKgTw8.', 'customer@example.com'),
('warehouse', 'warehouse', '$2a$12$I3geFmDqY9/xdhNA9CNhcO85xoCYbiRDZaQ.xGMCZwqZMDsXZb.vG', 'warehouse@example.com');


INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),  -- admin -> ADMIN
(2, 2),  -- guest -> GUEST
(3, 4),  -- manager -> MANAGER
(4, 3),  -- customer -> CUSTOMER
(5, 5);  -- warehouse -> WAREHOUSE