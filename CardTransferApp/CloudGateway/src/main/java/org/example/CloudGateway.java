package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGateway {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ClientAPI",r->r.path("/main/**")
                        .uri("http://localhost:8081/"))
                .route("UsersCardTransfer",r->r.path("/server/**")
                        .uri("http://localhost:8082/")).build();}
}