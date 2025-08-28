
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);


INSERT INTO users (name, email) VALUES
                                    ('João Silva', 'joao.silva@example.com'),
                                    ('Maria Santos', 'maria.santos@example.com'),
                                    ('Pedro Oliveira', 'pedro.oliveira@example.com');