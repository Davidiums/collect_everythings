package davidius.subscriptionservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feature extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la fonctionnalité
    private String name; // Nom de la fonctionnalité
    private String description; // Description de la fonctionnalité

}
