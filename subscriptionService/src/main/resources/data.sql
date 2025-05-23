INSERT INTO Plan (id, name, description, price) VALUES
                                                              (1, 'Basic Plan', 'A basic subscription plan', 9.99),
                                                              (2, 'Standard Plan', 'A standard subscription plan', 19.99),
                                                              (3, 'Premium Plan', 'A premium subscription plan', 29.99);

INSERT INTO Plan_features (Plan_id, features) VALUES
                                                  (1, 'Feature A'),
                                                  (1, 'Feature B'),
                                                  (2, 'Feature C'),
                                                  (2, 'Feature D'),
                                                  (3, 'Feature E'),
                                                  (3, 'Feature F');