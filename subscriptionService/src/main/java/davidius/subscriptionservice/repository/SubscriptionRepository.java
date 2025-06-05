package davidius.subscriptionservice.repository;

import davidius.subscriptionservice.entities.Subscription;
import davidius.subscriptionservice.entities.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find subscriptions by user ID or status
    List<Subscription> findByUserId(Long userId);

    List<Subscription> findByStatus(SubscriptionStatus status);
}
