CREATE DATABASE IF NOT EXISTS auth_db;
CREATE DATABASE IF NOT EXISTS client_db;
CREATE DATABASE IF NOT EXISTS paiement_db;
CREATE DATABASE IF NOT EXISTS subscription_db;
CREATE DATABASE IF NOT EXISTS item_db;
CREATE DATABASE IF NOT EXISTS basket_db;


CREATE USER IF NOT EXISTS 'auth_user'@'%' IDENTIFIED BY 'auth_password';
GRANT ALL PRIVILEGES ON auth_db.* TO 'auth_user'@'%';

CREATE USER IF NOT EXISTS 'client_user'@'%' IDENTIFIED BY 'client_password';
GRANT ALL PRIVILEGES ON client_db.* TO 'client_user'@'%';

CREATE USER IF NOT EXISTS 'paiement_user'@'%' IDENTIFIED BY 'paiement_password';
GRANT ALL PRIVILEGES ON paiement_db.* TO 'paiement_user'@'%';

CREATE USER IF NOT EXISTS 'subscription_user'@'%' IDENTIFIED BY 'subscription_password';
GRANT ALL PRIVILEGES ON subscription_db.* TO 'subscription_user'@'%';

CREATE USER IF NOT EXISTS 'item_user'@'%' IDENTIFIED BY 'item_password';
GRANT ALL PRIVILEGES ON item_db.* TO 'item_user'@'%';

CREATE USER IF NOT EXISTS 'basket_user'@'%' IDENTIFIED BY 'basket_password';
GRANT ALL PRIVILEGES ON basket_db.* TO 'basket_user'@'%';

FLUSH PRIVILEGES;
