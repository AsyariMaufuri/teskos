package com.Api.ApiGateway.repository;


import com.Api.ApiGateway.entity.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
    Mono<Account> findByUsername(String username);
}
