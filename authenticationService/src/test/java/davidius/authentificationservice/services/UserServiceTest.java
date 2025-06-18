package davidius.authentificationservice.services;

import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testFindByUsername_UserFound() {
        User user = new User();
        user.setUsername("toto");
        Mockito.when(userRepository.findByUsername("toto")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("toto");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("toto", result.getUsername());
    }

    @Test
    void testFindByUsername_UserNotFound() {
        Mockito.when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());
        User result = userService.findByUsername("unknown");
        Assertions.assertNull(result);
    }

    @Test
    void testFindByEmail_UserFound() {
        User user = new User();
        user.setEmail("test@test.fr");
        Mockito.when(userRepository.findByEmail("test@test.fr")).thenReturn(Optional.of(user));

        User result = userService.findByEmail("test@test.fr");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("test@test.fr", result.getEmail());
    }

    @Test
    void testFindByEmail_UserNotFound() {
        Mockito.when(userRepository.findByEmail("nope@test.fr")).thenReturn(Optional.empty());
        User result = userService.findByEmail("nope@test.fr");
        Assertions.assertNull(result);
    }

    @Test
    void testValidatePassword_Match() {
        // "password" -> BCrypt hash (exemple, génère-en un en vrai si besoin)
        String encoded = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("password");
        boolean isValid = userService.validatePassword("password", encoded);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testValidatePassword_NotMatch() {
        String encoded = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("password");
        boolean isValid = userService.validatePassword("wrong", encoded);
        Assertions.assertFalse(isValid);
    }

    @Test
    void testGetUserById_UserFound() {
        User user = new User();
        user.setId(123L);
        Mockito.when(userRepository.findById(123L)).thenReturn(Optional.of(user));
        User result = userService.getUserById(123L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(123L, result.getId());
    }

    @Test
    void testGetUserById_UserNotFound() {
        Mockito.when(userRepository.findById(666L)).thenReturn(Optional.empty());
        User result = userService.getUserById(666L);
        Assertions.assertNull(result);
    }
}
