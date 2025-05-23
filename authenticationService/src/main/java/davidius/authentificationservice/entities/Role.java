package davidius.authentificationservice.entities;

import com.davidius.shared.EntityUtils.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.envers.Audited;

import java.security.Permission;

@Getter
@Entity
@Audited
public class Role  extends AuditableEntity {
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
