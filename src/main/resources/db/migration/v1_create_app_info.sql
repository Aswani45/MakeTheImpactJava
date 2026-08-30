CREATE TABLE app_info (
    id BIGINT PRIMARY KEY,
    app_name VARCHAR(100) NOT NULL,
    admin_name VARCHAR(100) NOT NULL
);
INSERT INTO app_info
VALUES (1, 'My AWS Learning App', 'Admin');