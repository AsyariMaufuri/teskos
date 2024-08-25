package com.Api.ApiGateway.utility;

import com.Api.ApiGateway.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUserDetailsImplementation implements ReactiveUserDetailsService {
    private final AccountRepository repository;

    @Autowired
    public ReactiveUserDetailsImplementation(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(account -> (UserDetails) new UserDetailsImplementation(account))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")));
    }
}
