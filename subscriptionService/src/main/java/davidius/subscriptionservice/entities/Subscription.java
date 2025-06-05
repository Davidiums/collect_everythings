package davidius.subscriptionservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Subscription extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // ou une relation User si besoin
    private Long planId;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status; // ACTIVE, PENDING, CANCELLED, SUSPENDED

    private boolean paid; // ou montantPaid / nextPaymentDate, etc.

    private String paymentRef; // Optionnel : référence du paiement

    private LocalDateTime startDate;
    private LocalDateTime endDate; // ou date d’annulation/suspension

    // Ajoute createdAt/updatedAt si tu veux l’audit automatique
}
