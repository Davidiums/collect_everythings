package com.davidius.shared.security;

import com.davidius.shared.user.UserContext;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.Cookie;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

public class HeaderToSecurityContextFilter extends OncePerRequestFilter {
    @Override
    @Order(-500)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        if (jwt != null) {
            try {
                SignedJWT signedJWT = SignedJWT.parse(jwt);
                // Charge ta clé publique comme tu le faisais dans la gateway
                RSAPublicKey publicKey = KeyUtils.loadPublicKeyFromClasspath("keys/public_key.pem");
                JWSVerifier verifier = new RSASSAVerifier(publicKey);
                if (!signedJWT.verify(verifier)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                UserContext userContext = new UserContext();
                String userId = String.valueOf(signedJWT.getJWTClaimsSet().getClaim("id"));           // l’id BDD
                System.out.println("JWT userId: " + userId);
                String userMail = (String) signedJWT.getJWTClaimsSet().getClaim("email");
                System.out.println("JWT userMail: " + userMail);
                String role = (String) signedJWT.getJWTClaimsSet().getClaim("role");
                String firstName = (String) signedJWT.getJWTClaimsSet().getClaim("firstName");
                String lastName = (String) signedJWT.getJWTClaimsSet().getClaim("lastName");
                String displayName = signedJWT.getJWTClaimsSet().getSubject(); // "Prénom Nom" (sub)
                userContext.setMail(userMail);
                userContext.setFirstName(firstName);
                userContext.setLastName(lastName);
                userContext.setRole(role);
                userContext.setSubject(displayName);
                userContext.setAuthenticated(true);
                try {
                    userContext.setId(Long.parseLong(userId));
                } catch (Exception ignore) {
                    // Si l'id n'est pas un long, on ignore
                }
                UserContext.set(userContext);
                List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("AUTHENTICATED")); // rôle générique
                authorities.add(new SimpleGrantedAuthority(role));       // vrai rôle
                // afficher tout les roles de l'utilisateur
                System.out.println("JWT roles: " + authorities);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                signedJWT.getJWTClaimsSet().getSubject(),
                                null,
                                authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // log ou ignore
            }
        }
        filterChain.doFilter(request, response);
    }
}
