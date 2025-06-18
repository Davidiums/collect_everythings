package davidius.authentificationservice.controler;

import com.davidius.shared.user.UserContext;
import com.fasterxml.jackson.annotation.JsonView;
import davidius.authentificationservice.broker.responses.AuthenticationResponse;
import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.security.JwtUtils;
import davidius.authentificationservice.services.UserService;
import davidius.authentificationservice.views.PublicView;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final HttpServletResponse httpServletResponse;

    public AuthController(RabbitTemplate rabbitTemplate, UserService userService, JwtUtils jwtUtils, HttpServletResponse httpServletResponse) {
        this.rabbitTemplate = rabbitTemplate;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.httpServletResponse = httpServletResponse;
    }
    @JsonView(PublicView.class)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userToLogin) {
        System.out.println("AuthController: login");
        // loginRequest contient "email" et "password"
        User user = userService.findByEmail(userToLogin.getEmail());
        if (user == null || !userService.validatePassword(userToLogin.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Identifiants incorrects");
        }
        String token = jwtUtils.generateToken(user);

        // Création du cookie sécurisé
        Cookie authCookie = new Cookie("token", token);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(60 * 60);
        // Pour le SameSite, selon version Java/Spring, voir astuce ci-dessous

        httpServletResponse.addCookie(authCookie);
        return ResponseEntity.ok(Map.of("success", true, "message", "Authentification réussie", "user", user));
    }

    @PostMapping("/front-logout")
    @PreAuthorize("hasAuthority('AUTHENTICATED')")
    public ResponseEntity<?> frontLogout() {
        System.out.println("AuthController: logout");
        // Supprimer le cookie de session
        Cookie authCookie = new Cookie("token", null);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        httpServletResponse.addCookie(authCookie);
        return ResponseEntity.ok(Map.of("success", true, "message", "Déconnexion réussie"));
    }

     @GetMapping("/me")
     public ResponseEntity<?> getCurrentUser() {
         UserContext user = UserContext.get();
         if (user == null) {
             return ResponseEntity.status(401).body("Utilisateur non authentifié");
         }
         return ResponseEntity.ok(user);
     }

}
