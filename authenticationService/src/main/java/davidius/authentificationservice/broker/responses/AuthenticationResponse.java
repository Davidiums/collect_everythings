package davidius.authentificationservice.broker.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private boolean success; // Indique si l'authentification a réussi
    private String message;  // Message descriptif
    private String token;    // JWT généré (null si échec)
}