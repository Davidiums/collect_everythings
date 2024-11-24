package davidius.basketservice.Repositories;

import davidius.basketservice.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query("SELECT b FROM Basket b WHERE b.user_id = ?1")
    Optional<Basket> findByUserId(Long userId);

}
