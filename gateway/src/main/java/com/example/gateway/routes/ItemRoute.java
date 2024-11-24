package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemRoute {

    @Bean
    public RouteLocator itemRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("item_route", r -> r
                        .path("/item/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082"))
                .build();
    }

}
