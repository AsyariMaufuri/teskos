package com.example.TrollMarket.service.implement;

import com.example.TrollMarket.dto.AccountOptionDTO;
import com.example.TrollMarket.dto.account.*;
import com.example.TrollMarket.entitiy.Account;
import com.example.TrollMarket.repository.AccountRepository;
import com.example.TrollMarket.service.AccountService;
import com.example.TrollMarket.utility.JwtService;
import com.example.TrollMarket.utility.UserDetailsImplement;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AccountServiceImplement implements AccountService, UserDetailsService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AccountServiceImplement(AccountRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void register(AccountRegisterDTO accountRegisterDTO) {
        String hashPassword = passwordEncoder.encode(accountRegisterDTO.getPassword());
        Account account = new Account();
        account.setUsername(accountRegisterDTO.getUsername());
        account.setRole(accountRegisterDTO.getRole());
        account.setPassword(hashPassword);
        account.setAddress(accountRegisterDTO.getAddress());
        account.setName(accountRegisterDTO.getName());
        account.setBalance(accountRegisterDTO.getBalance());
        repository.save(account);
    }

    @Override
    public void topUpBalance(AccountTopUp accountTopUp) {
        Account account = repository.getAccountByUsername(accountTopUp.getUsername());
        account.setBalance(account.getBalance().add(accountTopUp.getBalance()));
        repository.save(account);
    }

    @Override
    public AccountTokenDTO createToken(AccountLoginDTO accountLoginDTO) {
        var account = repository.findById(accountLoginDTO.getUsername()).orElseThrow();
        if(!passwordEncoder.matches(accountLoginDTO.getPassword(), account.getPassword())){
            throw new IllegalArgumentException("Wrong username and password");
        };
        if (!accountLoginDTO.getRole().equals(account.getRole())){
            throw new IllegalArgumentException("Wrong Role");
        }
        String token = jwtService.generateToken(account);
        return new AccountTokenDTO(token);
    }

    @Override
    public List<AccountOptionDTO> getBuyerAllAccountOption() {
        List<Account> accounts = repository.getBuyerAccount();
        List<AccountOptionDTO> accountOptionDTOS = new LinkedList<>();
        for (Account account :accounts){
            AccountOptionDTO accountOptionDTO = new AccountOptionDTO();
            accountOptionDTO.setName(account.getName());
            accountOptionDTO.setUsername(account.getUsername());
            accountOptionDTOS.add(accountOptionDTO);
        }

        return accountOptionDTOS;
    }

    @Override
    public List<AccountOptionDTO> getSellerAllAccountOption() {
        List<Account> accounts = repository.getSellerAccount();
        List<AccountOptionDTO> accountOptionDTOS = new LinkedList<>();
        for (Account account :accounts){
            AccountOptionDTO accountOptionDTO = new AccountOptionDTO();
            accountOptionDTO.setName(account.getName());
            accountOptionDTO.setUsername(account.getUsername());
            accountOptionDTOS.add(accountOptionDTO);
        }

        return accountOptionDTOS;
    }

    @Override
    public boolean checkExistingUsername(String username) {
        Account account = repository.getAccountByUsername(username);
        if (account != null){
            return true;
        }else {

            return false;

        }

    }


    @Override
    public AccountDTO findAccountByUsername(String username) {
        Account account = repository.getAccountByUsername(username);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setName(account.getName());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setRole(account.getRole());
        accountDTO.setOrders(account.getOrders());
        return accountDTO;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findById(username).orElseThrow();
        return new UserDetailsImplement(account);
    }




}
