package davidius.itemservice.DAO;

import davidius.itemservice.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends JpaRepository<Item, Long> {

    List<Item> searchByName(String name);
}
