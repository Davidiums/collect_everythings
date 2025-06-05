package davidius.paymentService.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentMethod extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private Long userId; // ton id utilisateur interne

    @Enumerated(EnumType.STRING)
    private PaymentProvider provider; // ex: "stripe"

    private String externalPaymentMethodId; // id Stripe du payment method
    private String externalCustomerId; // id Stripe du customer
    private boolean active;
}
