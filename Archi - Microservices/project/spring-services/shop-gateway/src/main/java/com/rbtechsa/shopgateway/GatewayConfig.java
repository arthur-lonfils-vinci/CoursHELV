package com.rbtechsa.shopgateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class GatewayConfig {

    // Define a bean for IP-based key resolution
    @Bean
    public KeyResolver ipAddressKeyResolver() {
        // This resolver uses the client's IP address as the key for rate limiting
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }

    // You can have other KeyResolver beans for user ID, API Key, etc.
    // @Bean
    // public KeyResolver userKeyResolver() {
    //     return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("X-User-ID"));
    // }
}
