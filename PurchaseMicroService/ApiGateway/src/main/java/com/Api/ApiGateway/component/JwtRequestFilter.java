package com.Api.ApiGateway.component;

import com.Api.ApiGateway.utility.JwtService;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtRequestFilter implements WebFilter {

    private final JwtService jwtService;
    private final ReactiveUserDetailsService reactiveUserDetailsService;

    @Autowired
    public JwtRequestFilter(JwtService jwtService, ReactiveUserDetailsService reactiveUserDetailsService) {
        this.jwtService = jwtService;
        this.reactiveUserDetailsService = reactiveUserDetailsService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                Claims claims = jwtService.getClaims(token);
                String username = claims.get("username", String.class);

                return reactiveUserDetailsService.findByUsername(username)
                        .filter(userDetails -> jwtService.isValid(token, userDetails))
                        .flatMap(userDetails -> {
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            return chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
                        }).switchIfEmpty(Mono.defer(() -> unauthorized(exchange)));
            } catch (Exception e) {
                return unauthorized(exchange);
            }
        }

        return chain.filter(exchange);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
