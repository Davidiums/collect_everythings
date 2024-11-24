package davidius.authentificationservice.broker.consumer;

import davidius.authentificationservice.broker.RabbitMQConfig;
import davidius.authentificationservice.broker.requests.AuthenticationRequest;
import davidius.authentificationservice.broker.responses.AuthenticationResponse;
import davidius.authentificationservice.entities.User;
import davidius.authentificationservice.security.JwtUtils;
import davidius.authentificationservice.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuthConsumer {
    private final JwtUtils jwtUtils; // Service pour gérer les JWT
    private final UserService userService; // Service pour interagir avec les utilisateurs (BDD ou autre)

    public AuthConsumer(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @RabbitListener(queues = "authQueue")
    public AuthenticationResponse processAuthRequest(AuthenticationRequest request) {
        System.out.println("Requête d'authentification reçue : " + request.getUsername());
        // Vérifier si l'utilisateur existe
        User user = userService.findByUsername(request.getUsername());
        if (user == null || !userService.validatePassword(request.getPassword(), user.getPassword())) {
            System.out.println("Échec de l'authentification pour " + request.getUsername());
            return new AuthenticationResponse(false, "Échec de l'authentification : Identifiants incorrects", null);
        }

        // Générer un JWT
        String token = jwtUtils.generateToken(user.getUsername());
        System.out.println("Authentification réussie pour " + request.getUsername());

        return new AuthenticationResponse(true, "Authentification réussie", token);
    }

    @RabbitListener(queues = RabbitMQConfig.GET_USER_FROM_TOKEN_QUEUE)
    public long validateJWT(String jwt) {
        System.out.println("Requête de validation de JWT reçue : " + jwt);
        try {
            // Décoder le JWT et extraire l'ID utilisateur
            return jwtUtils.getUserIdFromToken(jwt);
        } catch (Exception e) {
            return -1;
        }
    }

}