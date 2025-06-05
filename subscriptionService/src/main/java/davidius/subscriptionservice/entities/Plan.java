package davidius.subscriptionservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Plan extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double price;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "plan_features",
//        joinColumns = @JoinColumn(name = "plan_id"),
//        inverseJoinColumns = @JoinColumn(name = "feature_id")
//    )
//    private List<Feature> features; // Liste des fonctionnalités associées au plan
}