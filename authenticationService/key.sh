# Génère la clé privée (2048 bits)
openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048

# Génère la clé publique à partir de la privée
openssl rsa -pubout -in private_key.pem -out public_key.pem