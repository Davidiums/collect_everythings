package davidius.authentificationservice.security;

import davidius.authentificationservice.entities.Role;
import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

class MyUserDetailsServiceTest {

    private UserRepository userRepository;
    private MyUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = new MyUserDetailsService(userRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        User user = new User();
        user.setEmail("test@test.fr");
        user.setUsername("toto");
        user.setPassword("password");
        user.setRole(new Role("USER"));
        Mockito.when(userRepository.findByEmail("test@test.fr")).thenReturn(Optional.of(user));

        UserDetails details = userDetailsService.loadUserByUsername("test@test.fr");
        Assertions.assertEquals("test@test.fr", details.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        Mockito.when(userRepository.findByEmail("nope@test.fr")).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nope@test.fr");
        });
    }
}
