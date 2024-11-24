package davidius.basketservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Setter
    long user_id;

    @Setter
    @ElementCollection
    List<Long> item_ids;

    public void addItem(long itemId) {
        item_ids.add(itemId);
    }

    public void removeItem(long itemId) {
        item_ids.remove(itemId);
    }
}

