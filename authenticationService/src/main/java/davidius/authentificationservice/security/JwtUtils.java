package davidius.authentificationservice.security;

import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.repositories.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.repositories.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey; // Si besoin pour la validation locale (rare côté auth)
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

        private final UserRepository userRepository;
        @Getter
        private final PrivateKey privateKey;
        private final PublicKey publicKey; // Ajoute la clé publique ici
        private final long jwtExpirationInMs = 3600000; // 1 heure

        public JwtUtils(UserRepository userRepository) {
            this.userRepository = userRepository;
            try {
                this.privateKey = KeyUtils.loadPrivateKey();
                this.publicKey = KeyUtils.loadPublicKey();
            } catch (Exception e) {
                throw new RuntimeException("Impossible de charger les clés JWT", e);
            }
        }

        public String generateToken(User user) {
            System.out.println("pute");

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

            return Jwts.builder()
                    .setSubject(user.getUsername()) // <-- le nom d'utilisateur
                    .claim("id", user.getId())      // <-- l'id BDD
                    .claim("role", user.getRole().getName())
                    .claim("email", user.getEmail())   // si tu veux l'avoir partout aussi
                    .claim("firstName", user.getFirstName()) // si tu veux, optionnel
                    .claim("lastName", user.getLastName())   // optionnel aussi
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

        }

        public String getUsernameFromToken(String token) {
            // Utilise la clé publique directement
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public long getUserIdFromToken(String token) {
            String username = getUsernameFromToken(token);
            Optional<User> user = userRepository.findByUsername(username);
            return user.map(User::getId).orElse(-1L);
        }

        // Si besoin, tu laisses aussi la version qui prend une clé explicite (pour tests ou usages avancés)
        public String getUsernameFromToken(String token, PublicKey pubKey) {
            return Jwts.parserBuilder()
                    .setSigningKey(pubKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public long getUserIdFromToken(String token, PublicKey pubKey) {
            String username = getUsernameFromToken(token, pubKey);
            Optional<User> user = userRepository.findByUsername(username);
            return user.map(User::getId).orElse(-1L);
        }

}