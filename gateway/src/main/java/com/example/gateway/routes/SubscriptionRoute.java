package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionRoute {

    @Bean
    public RouteLocator subscriptionRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("subscription_route", r -> r
                        .path("/subscription/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082"))
                .build();
    }
}
