-- Insertion des catégories
INSERT INTO category (id, name, description) VALUES
                                                 (1, 'Électronique', 'Appareils électroniques et gadgets'),
                                                 (2, 'Livres', 'Une variété de livres'),
                                                 (3, 'Vêtements', 'Vêtements pour hommes et femmes');

-- Insertion des articles
INSERT INTO item (id, name, description, category_id, price, stock) VALUES
                                                                        (1, 'Smartphone', 'Un smartphone de dernière génération', 1, 699.99, 50),
                                                                        (2, 'Ordinateur Portable', 'Un ordinateur portable puissant', 1, 1299.99, 30),
                                                                        (3, 'Roman Policier', 'Un roman policier captivant', 2, 15.99, 100),
                                                                        (4, 'T-shirt', 'Un t-shirt en coton confortable', 3, 9.99, 200);

-- Insertion des images
INSERT INTO image (id, url, description) VALUES
                                             (1, 'http://exemple.com/images/smartphone.jpg', 'Image du smartphone'),
                                             (2, 'http://exemple.com/images/ordinateur_portable.jpg', 'Image de l\'ordinateur portable'),
                                             (3, 'http://exemple.com/images/roman_policier.jpg', 'Image du roman policier'),
                                             (4, 'http://exemple.com/images/tshirt.jpg', 'Image du t-shirt');

-- Insertion dans la table de liaison pour la relation ManyToMany entre Item et Image
INSERT INTO item_images (item_id, images_id) VALUES
                                               (1, 1),
                                               (2, 2),
                                               (3, 3),
                                               (4, 4);

-- Si un article a plusieurs images ou une image est liée à plusieurs articles
-- Exemples supplémentaires :
INSERT INTO image (id, url, description) VALUES
    (5, 'http://exemple.com/images/smartphone_dos.jpg', 'Image du dos du smartphone');

INSERT INTO item_images (item_id, images_id) VALUES
    (1, 5);
