package com.holsync.security.apigateway.filters;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */
import com.holsync.security.apigateway.util.JwtUtil;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    JwtUtil jwtUtil;

    // ✅ Define the endpoints to bypass authentication
    private static final List<String> BYPASS_URLS = List.of(
            "/auth/signup",
            "/auth/signin",
            "/eureka"
    );

    public static class Config{

    }

    public AuthFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            // ✅ If the request matches a bypassed URL, skip authentication
            if(BYPASS_URLS.stream().anyMatch(path::startsWith)){
               return chain.filter(exchange); // Proceed without JWT validation
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return handleUnauthorized(exchange);
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix

            Boolean isTokenValid = jwtUtil.validateToken(token);

            if(isTokenValid){
                return chain.filter(exchange);
            }else {
                return handleUnauthorized(exchange);
            }
        });
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }



}
