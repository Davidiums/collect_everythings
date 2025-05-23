package davidius.authentificationservice.controler;

import davidius.authentificationservice.broker.RabbitMQConfig;
import davidius.authentificationservice.broker.requests.AuthenticationRequest;
import davidius.authentificationservice.broker.responses.AuthenticationResponse;
import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.security.JwtUtils;
import davidius.authentificationservice.services.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(RabbitTemplate rabbitTemplate, UserService userService, JwtUtils jwtUtils) {
        this.rabbitTemplate = rabbitTemplate;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userToLogin) {
        // loginRequest contient "email" et "password"
        User user = userService.findByEmail(userToLogin.getEmail());
        if (user == null || !userService.validatePassword(userToLogin.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Identifiants incorrects");
        }
        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(true, "Authentification réussie", token));
    }


    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        System.out.println("AuthController: login");
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/")
     public String home() {
         System.out.println("nskjdnwmndkjnwekd wklndwkelkx");
         return "index";
     }
}
