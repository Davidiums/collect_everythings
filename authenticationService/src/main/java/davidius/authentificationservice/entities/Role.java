package davidius.authentificationservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import java.security.Permission;

@Getter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String name;
    private Permission permission;

    public Role(String roleName) {
        this.name = roleName;
    }

    public Role() {

    }
}
