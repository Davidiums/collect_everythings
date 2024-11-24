package davidius.itemservice.DAO;

import davidius.itemservice.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageDAO extends JpaRepository<Image, Long>{

    @Query("SELECT i FROM Image i WHERE i.item.id = ?1")
    Optional<List<Image>> getImageForItem(long itemId);
}
