package davidius.authentificationservice.controler;

import davidius.authentificationservice.broker.RabbitMQConfig;
import davidius.authentificationservice.broker.requests.AuthenticationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final RabbitTemplate rabbitTemplate;

    public AuthController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            // Créer une requête d'authentification
            AuthenticationRequest request = new AuthenticationRequest(username, password);

            Object response = rabbitTemplate.convertSendAndReceive(
                    RabbitMQConfig.EXCHANGE, // Envoyer la requête à l'échange
                    RabbitMQConfig.ROUTING_KEY, // Utiliser la clé de routage
                    request // Envoyer la requête
            );

            // Vérifier la réponse
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(500).body("Pas de réponse du service d'authentification.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'envoi à RabbitMQ.");
        }
    }
}
