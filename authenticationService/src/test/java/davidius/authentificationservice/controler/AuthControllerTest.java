package davidius.authentificationservice.controler;

import com.davidius.shared.user.UserContext;
import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.security.JwtUtils;
import davidius.authentificationservice.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private UserService userService;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private HttpServletResponse httpServletResponse;

    @InjectMocks
    private AuthController authController;

    @Test
    void testLogin_Success() {
        User userInput = new User();
        userInput.setEmail("test@test.fr");
        userInput.setPassword("password");

        User userInDb = new User();
        userInDb.setEmail("test@test.fr");
        userInDb.setPassword("hashedpwd"); // le hash stocké

        Mockito.when(userService.findByEmail("test@test.fr")).thenReturn(userInDb);
        Mockito.when(userService.validatePassword("password", "hashedpwd")).thenReturn(true);
        Mockito.when(jwtUtils.generateToken(userInDb)).thenReturn("fakeToken");

        ResponseEntity<?> resp = authController.login(userInput);

        Assertions.assertEquals(200, resp.getStatusCodeValue());
        Assertions.assertTrue(resp.getBody().toString().contains("Authentification réussie"));
        Mockito.verify(httpServletResponse).addCookie(Mockito.any(Cookie.class));
    }

    @Test
    void testLogin_Failure() {
        User userInput = new User();
        userInput.setEmail("wrong@test.fr");
        userInput.setPassword("badpassword");

        Mockito.when(userService.findByEmail("wrong@test.fr")).thenReturn(null);

        ResponseEntity<?> resp = authController.login(userInput);

        Assertions.assertEquals(401, resp.getStatusCodeValue());
        Assertions.assertEquals("Identifiants incorrects", resp.getBody());
    }

    @Test
    void testFrontLogout() {
        // Arrange
        // (httpServletResponse est déjà un mock)

        // Act
        ResponseEntity<?> resp = authController.frontLogout();

        // Assert
        Assertions.assertEquals(200, resp.getStatusCodeValue());
        Assertions.assertTrue(resp.getBody().toString().contains("Déconnexion réussie"));
        Mockito.verify(httpServletResponse).addCookie(Mockito.argThat(cookie ->
                cookie.getName().equals("token") &&
                        cookie.getValue() == null &&
                        cookie.getMaxAge() == 0 &&
                        cookie.isHttpOnly() &&
                        cookie.getPath().equals("/")
        ));
    }

    @Test
    void testGetCurrentUser_Unauthenticated() {
        // Arrange
        UserContext.set(null);

        // Act
        ResponseEntity<?> resp = authController.getCurrentUser();

        // Assert
        Assertions.assertEquals(401, resp.getStatusCodeValue());
        Assertions.assertEquals("Utilisateur non authentifié", resp.getBody());
    }

    @Test
    void testGetCurrentUser_Authenticated() {
        // Arrange
        UserContext user = new UserContext();
        user.setMail("toto@test.fr");
        user.setId(1L);
        UserContext.set(user);

        // Act
        ResponseEntity<?> resp = authController.getCurrentUser();

        // Assert
        Assertions.assertEquals(200, resp.getStatusCodeValue());
        Assertions.assertEquals(user, resp.getBody());
    }

}
