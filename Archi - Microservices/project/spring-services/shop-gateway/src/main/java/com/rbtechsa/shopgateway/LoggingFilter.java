package com.rbtechsa.shopgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//Makes this class a Spring bean, so Spring detects and registers it.
@Component
// GlobalFilter -> This interface indicates that this filter should be applied globally to all requests passing through the gateway.
// Ordered -> This interface allows you to specify the execution order of your global
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    // ServerWebExchange
    // Represents the current request-response interaction.
    // You can get the ServerHttpRequest (incoming request) and ServerHttpResponse (outgoing response) from it.
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // --- Log before routing (pre-filter logic) ---
        logger.info("Gateway Pre-Filter: Request ID: {} - Path: {} - Method: {} - From IP: {}",
                request.getId(), request.getURI().getPath(), request.getMethod(), request.getRemoteAddress());

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // --- Log after routing (post-filter logic) ---
                    // This part executes after the downstream service has responded
                    logger.info("Gateway Post-Filter: Response Status: {} for Request ID: {}",
                            exchange.getResponse().getStatusCode(), request.getId());
                }));
    }

    @Override
    public int getOrder() {
        // This determines the order in which global filters are executed.
        // A lower number means higher precedence (executes earlier).
        // We'll set it to -1 to ensure it runs very early.
        return -1;
    }
}
