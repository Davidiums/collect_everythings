package davidius.authentificationservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import davidius.authentificationservice.views.PublicView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;


@Getter
@Setter
@Entity
@Audited
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PublicView.class)
    private long id;

    @JsonView(PublicView.class)
    private String username;
    private String password;
    @JsonView(PublicView.class)
    private String email;
    @JsonView(PublicView.class)
    private String firstName;
    @JsonView(PublicView.class)
    private String lastName;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new Role("USER");
    }

    public User() {

    }
}
