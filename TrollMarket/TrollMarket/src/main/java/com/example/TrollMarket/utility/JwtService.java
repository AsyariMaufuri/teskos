package com.example.TrollMarket.utility;

import com.example.TrollMarket.entitiy.Account;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(Account account);
    Boolean isValid(String token, UserDetails userDetails);
    Claims getClaims(String token);
}
