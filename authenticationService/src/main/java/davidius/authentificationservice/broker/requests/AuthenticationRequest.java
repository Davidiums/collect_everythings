package davidius.authentificationservice.broker.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest  implements Serializable {
    private static final long serialVersionUIDLONG = 1L;
    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

