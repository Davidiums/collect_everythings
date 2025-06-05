package davidius.paymentService.Consumers;

import com.davidius.shared.event.PaymentRequestedEvent;
import com.davidius.shared.event.PaymentResultEvent;
import davidius.paymentService.services.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultConsumer  {

    private final PaymentService paymentService;
    private final RabbitTemplate rabbitTemplate;

    public PaymentResultConsumer(PaymentService paymentService, RabbitTemplate rabbitTemplate) {
        this.paymentService = paymentService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "payment.requested")
    public void handlePaymentRequested(PaymentRequestedEvent event) {
        // 1. Tenter le paiement (simulateur ou Stripe)
        boolean ok = tryPayment(event);
        // 2. Sauver le paiement si succès
        if (ok) paymentService.savePayment(event.getUserId(), event.getAmount());
        // 3. Publier le résultat
        PaymentResultEvent result = new PaymentResultEvent();
        result.setUserId(event.getUserId());
        result.setSubscriptionId(event.getSubscriptionId());
        result.setAmount(event.getAmount());
        result.setStatus(ok ? "confirmed" : "failed");
        rabbitTemplate.convertAndSend("payment.result", result);
    }

    private boolean tryPayment(PaymentRequestedEvent event) {
        // Ici tu fais l’appel Stripe ou la simulation. Pour le POC :
        System.out.println("Tentative de paiement simulée pour userId=" + event.getUserId() + ", amount=" + event.getAmount());
        return true; // ou random pour tester les cas KO
    }

}
