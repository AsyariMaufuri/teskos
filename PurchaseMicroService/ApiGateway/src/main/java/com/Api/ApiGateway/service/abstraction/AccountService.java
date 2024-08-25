package com.Api.ApiGateway.service.abstraction;


import com.Api.ApiGateway.entity.Account;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> findByUsername(String username);
}
