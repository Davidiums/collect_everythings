package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationRoutes {

    @Bean
    public RouteLocator authRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentification_route", r -> r
                        // Inclure tout sauf /auth/gateway
                        .path("/auth/**")
                        .and()
                        .not(rp -> rp.path("/auth/gateway/**")) // Exclut /auth/gateway et ses sous-routes
                        .filters(f -> f.stripPrefix(1)) // Supprime le préfixe "/auth"
                        .uri("http://localhost:8081")) // Redirige vers le service d'authentification
                .build();
    }
}