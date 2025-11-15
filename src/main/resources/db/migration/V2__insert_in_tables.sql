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
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON 1=1
WHERE r.role_name = 'ADMIN';

-- Только просмотр продуктов для GUEST
INSERT INTO roles_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.permission_name = 'VIEW_PRODUCTS'
WHERE r.role_name = 'GUEST';

-- Вставка пользователей с динамическим role_id
INSERT INTO users (username, fullname, password, email, role_id) VALUES
('admin', 'admin', '$2a$12$HxsLYYIrUuEVegg90Vgb8eWzKMOQ91fxyOlTJ2bVhWQxjujPtkskq', 'admin@example.com',
    (SELECT id FROM roles WHERE role_name = 'ADMIN')
),
('guest', 'guest', '$2a$12$j3wFFvnrO7TRxba91Bu1duIp0UdIjN9mVoiK3LWq1Sjmi6Po0R98.', 'guest@example.com',
    (SELECT id FROM roles WHERE role_name = 'GUEST')
),
('manager', 'manager', '$2a$12$Jh8RKx4YnATCqeLn6iqGb.qZI245SP2cl8lBSFf7yhZ0doJOaHdhK', 'manager@example.com',
    (SELECT id FROM roles WHERE role_name = 'MANAGER')
),
('customer', 'customer', '$2a$12$.TKTMyFH.sv2OVs.fNAM6.di7jPL23thrsSKm7WtvZXpBReKgTw8.', 'customer@example.com',
    (SELECT id FROM roles WHERE role_name = 'CUSTOMER')
),
('warehouse', 'warehouse', '$2a$12$I3geFmDqY9/xdhNA9CNhcO85xoCYbiRDZaQ.xGMCZwqZMDsXZb.vG', 'warehouse@example.com',
    (SELECT id FROM roles WHERE role_name = 'WAREHOUSE')
);