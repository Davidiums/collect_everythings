INSERT INTO role (id, name) VALUES
                                (1, 'USER'),
                                (2, 'ADMIN'),
                                (3, 'CACATOES');


INSERT INTO user (id, username, password, role_id) VALUES
                                                       (1, 'user1', '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 1), -- Mot de passe : 123456
                                                       (2, 'admin1', '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 2), -- Mot de passe : 123456
                                                       (3, 'cacatoe1', '$2a$12$bfkjuyvn2kqxdfgLLCTyQOLsUwVYBXOkmLY2MqSe4Wtl0AaIgFWfm', 3); -- Mot de passe : 123456
