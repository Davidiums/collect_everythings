package davidius.itemservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    private String description;

    @ManyToOne
    private Item item;

    public Image() {

    }
}
