package davidius.authentificationservice.security;

import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.repositories.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    private final UserRepository userRepository;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



//    @Value("${jwt.secret}")
    private final String jwtSecret = "your-256-bit-secret-your-256-bit-secret";
//    @Value("${jwt.expiration}")
    private final long jwtExpirationInMs = 3600000; // 1 heure
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Génère une clé sécurisée

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512) // Utilisation de Key
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Utilisation de Key
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Utilisation de Key
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
}
