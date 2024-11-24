package davidius.basketservice.services;
import davidius.basketservice.broker.RabbitMQConfig;
import davidius.basketservice.exceptions.UnknowUserException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final RabbitTemplate rabbitTemplate;

    public AuthService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Long getUserIdFromJWT(String jwt) throws UnknowUserException {
        try {
            // Envoyer le JWT à la queue GetUserFromToken
            Object response = rabbitTemplate.convertSendAndReceive(
                    RabbitMQConfig.GET_USER_FROM_TOKEN_QUEUE, // Nom de la queue
                    jwt // Le JWT à vérifier
            );
            if (response != null) {
                if (response instanceof Long) {
                    System.out.println("ID utilisateur extrait (Long) : " + response);
                    return (Long) response; // Directement retourner l'objet Long
                } else if (response instanceof String) {
                    System.out.println("ID utilisateur extrait (String) : " + response);
                    return Long.parseLong((String) response); // Convertir en Long si c'est une String
                } else {
                    throw new ClassCastException("Type inattendu pour la réponse : " + response.getClass());
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction de l'ID utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
        return null; // En cas d'erreur ou de JWT invalide
    }

}