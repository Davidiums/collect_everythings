package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasketRoute {

    @Bean
    public RouteLocator basketRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("basket_route", r -> r
                        .path("/basket/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8083"))
                .build();
    }
}
