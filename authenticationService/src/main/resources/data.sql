INSERT INTO role (id, name) VALUES
                                (1, 'USER'),
                                (2, 'ADMIN'),
                                (3, 'CACATOES');


INSERT INTO user (username, password, email, first_name, last_name, role_id) VALUES
    ('jean.dupont',  '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 'user1@test.fr', 'Jean', 'Dupont', 1),
    ('sophie.admin', '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 'sophie.admin@email.com', 'Sophie', 'Admin', 2),
    ('paul.user',    '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 'paul.user@email.com', 'Paul', 'User', 1);
