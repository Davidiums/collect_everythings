package davidius.authentificationservice.security;

import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.entities.Role;
import davidius.authentificationservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        // Mock du UserRepository
        userRepository = Mockito.mock(UserRepository.class);

        // Les clés de test doivent être dans src/test/resources ou générées pour l'occasion
        // KeyUtils.loadPrivateKey/loadPublicKey doit pointer dessus !
        jwtUtils = new JwtUtils(userRepository);
    }

    @Test
    void testGenerateAndParseToken() {
        // Arrange : crée un user test
        User user = new User();
        user.setId(42L);
        user.setEmail("toto@bidule.com");
        user.setUsername("toto");
        user.setFirstName("Toto");
        user.setLastName("Bidule");
        Role role = new Role("ADMIN");
        user.setRole(role);

        Mockito.when(userRepository.findByUsername("toto")).thenReturn(Optional.of(user));

        // Act : génère un token
        String token = jwtUtils.generateToken(user);
        assertNotNull(token);

        // Vérifie que tu peux parser le token
        String username = jwtUtils.getUsernameFromToken(token);
        assertEquals("toto", username);

        long userId = jwtUtils.getUserIdFromToken(token);
        assertEquals(42L, userId);
    }

    @Test
    void testGetUserIdFromToken_userNotFound() {
        // Arrange
        String token = Jwts.builder()
                .setSubject("nobody")
                .signWith(jwtUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();

        Mockito.when(userRepository.findByUsername("nobody")).thenReturn(Optional.empty());

        // Act
        long userId = jwtUtils.getUserIdFromToken(token);

        // Assert
        assertEquals(-1L, userId);
    }
}
