package davidius.paymentService.services;

import com.davidius.shared.event.EventEntityRepository;
import davidius.paymentService.entities.Payment;
import davidius.paymentService.repositories.PaymentMethodRepository;
import davidius.paymentService.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    EventEntityRepository eventEntityRepository;
    PaymentMethodRepository paymentMethodRepository;
    PaymentRepository paymentRepository;

    public PaymentService(EventEntityRepository eventEntityRepository,
                          PaymentMethodRepository paymentMethodRepository,
                          PaymentRepository paymentRepository) {
        this.eventEntityRepository = eventEntityRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
    }

    public void savePayment(Long userId, Double amount) {
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }

}
