package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrchestratorRoute {

    @Bean
    public RouteLocator orchetratorRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("Initializing Orchestrator Route...");
        return builder.routes()
                .route("orchestrator_route", r -> r
                        .path("/orchestrator/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8089"))
                .build();
    }
}
