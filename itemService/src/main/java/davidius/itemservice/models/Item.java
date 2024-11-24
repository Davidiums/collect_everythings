package davidius.itemservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import davidius.itemservice.views.AdminView;
import davidius.itemservice.views.CustomerView;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomerView.class, AdminView.class})
    private long id;

    @JsonView({CustomerView.class, AdminView.class})
    private String name;

    @JsonView({CustomerView.class, AdminView.class})
    private String description;

    @ManyToOne
    @JsonView({CustomerView.class, AdminView.class})
    private Category category;

    @JsonView({CustomerView.class, AdminView.class})
    private double price;

    @JsonView({CustomerView.class, AdminView.class})
    private int stock;

    @OneToMany
    private List<Image> images;
}
