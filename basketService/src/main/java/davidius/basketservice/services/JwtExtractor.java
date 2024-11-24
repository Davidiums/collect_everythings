package davidius.basketservice.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
public class JwtExtractor {

    public String extractJWT(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader != null ? "Auth header: " + authHeader : "No auth header");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Supprime le préfixe "Bearer "
        }
        return null;
    }
}
