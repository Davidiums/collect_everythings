package davidius.authentificationservice.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {

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
