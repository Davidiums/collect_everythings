package davidius.paymentService.repositories;

import davidius.paymentService.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find payment methods by user ID or type
    List<PaymentMethod> findByUserId(Long userId);

    List<PaymentMethod> findByType(String type);
}
