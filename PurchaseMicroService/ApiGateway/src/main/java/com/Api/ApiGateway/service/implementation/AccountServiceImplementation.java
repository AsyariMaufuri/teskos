package com.Api.ApiGateway.service.implementation;

import com.Api.ApiGateway.entity.Account;
import com.Api.ApiGateway.repository.AccountRepository;
import com.Api.ApiGateway.service.abstraction.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class AccountServiceImplementation implements AccountService {
    private final AccountRepository repository;

    @Autowired
    public AccountServiceImplementation(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Account> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
