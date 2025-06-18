//package com.example.gateway.configs;
//
//import com.example.gateway.utils.KeyUtils;
//import com.nimbusds.jwt.SignedJWT;
//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jose.crypto.RSASSAVerifier;
//import java.security.interfaces.RSAPublicKey;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//
//
//@Configuration
//public class JwtToHeaderGatewayConfig {
//
//    @Bean
//    @Order(-9999)
//    public GlobalFilter jwtToHeaderFilter() throws Exception {
//        // Charge la clé publique UNE FOIS pour tout le filtre
//        final RSAPublicKey publicKey = KeyUtils.loadPublicKeyFromClasspath("keys/public_key.pem");
//
//        return (exchange, chain) -> {
//            String token;
//            if(exchange.getRequest().getCookies().getFirst("token") != null ){
//                token = exchange.getRequest().getCookies().getFirst("token").getValue();
//
//                try {
//                    SignedJWT signedJWT = SignedJWT.parse(token);
//
//                    // Vérifie la signature JWT
//                    JWSVerifier verifier = new RSASSAVerifier(publicKey);
//                    boolean valid = signedJWT.verify(verifier);
//                    if (!valid) {
//                        System.out.println("JWT signature is invalid");
//                        // Signature invalide : ignore, log, ou renvoie une 401 selon ta politique
//                        return chain.filter(exchange); // Ou exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
//                    }
//                    // Si la signature est valide, extrait les informations du JWT
//                    String userId = String.valueOf(signedJWT.getJWTClaimsSet().getClaim("id"));           // l’id BDD
//                    System.out.println("JWT userId: " + userId);
//                    String userMail = (String) signedJWT.getJWTClaimsSet().getClaim("email");
//                    System.out.println("JWT userMail: " + userMail);
//                    String role = (String) signedJWT.getJWTClaimsSet().getClaim("role");
//                    String firstName = (String) signedJWT.getJWTClaimsSet().getClaim("firstName");
//                    String lastName = (String) signedJWT.getJWTClaimsSet().getClaim("lastName");
//                    String displayName = signedJWT.getJWTClaimsSet().getSubject(); // "Prénom Nom" (sub)
//
//
//                    ServerHttpRequest mutated = exchange.getRequest().mutate()
//                            .header("X-User-Id", userId)
//                            .header("X-User-Mail", userMail)
//                            .header("X-User-Role", role)
//                            .header("X-User-Name", displayName)
//                            .header("X-User-FirstName", firstName)
//                            .header("X-User-LastName", lastName)
//                            .build();
//
//                    return chain.filter(exchange.mutate().request(mutated).build());
//                } catch (Exception e) {
//                    // Token malformé ou signature impossible, ignore ou log si besoin
//                }
//            }
//            return chain.filter(exchange);
//        };
//    }
//}