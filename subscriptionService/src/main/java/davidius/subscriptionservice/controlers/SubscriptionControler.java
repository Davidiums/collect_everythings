package davidius.subscriptionservice.controlers;

import com.davidius.shared.user.UserContext;
import davidius.subscriptionservice.entities.Subscription;
import davidius.subscriptionservice.entities.SubscriptionStatus;
import davidius.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public class SubscriptionControler {

    private final SubscriptionRepository subscriptionRepository;
    private final RabbitTemplate rabbitTemplate;

    public SubscriptionControler(
            SubscriptionRepository subscriptionRepository,
            RabbitTemplate rabbitTemplate

    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/subscribe")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> subscribeToPlan(@RequestHeader("planId") long planId) {
        Long userId = UserContext.get().getId();
        Subscription sub = new Subscription();
        sub.setUserId(userId);
        sub.setPlanId(planId);
        sub.setStatus(SubscriptionStatus.PENDING);
        sub.setStartDate(LocalDateTime.now());
        subscriptionRepository.save(sub);

        // Crée le message (ici juste un String, mais tu peux envoyer un objet JSON)
        String message = "Demande de paiement pour userId " + userId + ", planId " + planId;

        // Envoie dans la queue (par exemple payment.confirmed, ou crée payment.requested pour être plus logique)
        rabbitTemplate.convertAndSend("payment.confirmed", message);

        return ResponseEntity.ok("Abonnement créé et demande de paiement envoyée !");
    }

}
