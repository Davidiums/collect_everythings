package davidius.authentificationservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.envers.Audited;


@Getter
@Entity
@Audited
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private String firstName;
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
