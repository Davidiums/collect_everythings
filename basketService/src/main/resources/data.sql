-- Insérer un panier pour l'utilisateur avec user_id = 1
INSERT INTO basket (user_id) VALUES (1);

--

-- Insérer les items dans le panier
INSERT INTO basket_item_ids (basket_id, item_ids) VALUES
                                                      (1, 1), -- Premier item avec ID 1
                                                      (1, 1), -- Deuxième item avec ID 1
                                                      (1, 2); -- Item avec ID 2
