package com.msa.eureka.cilent.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Date;

public class AuthenticationFilter implements GlobalFilter {

    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/auth/signIn") || path.equals("/auth/signUp")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null || !validateToken(token, exchange)) {
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.matches("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token, ServerWebExchange exchange) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token).getPayload();
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            exchange.getRequest().mutate()
                    .header("X-User-Id", claims.get("user_id").toString())
                    .header("X-Role", claims.get("role").toString())
                    .build();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}