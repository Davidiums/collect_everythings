package davidius.paymentService.repositories;

import davidius.paymentService.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find payments by user ID or date range
    List<Payment> findByUserId(Long userId);

    List<Payment> findByAmountGreaterThan(Double amount);
}
