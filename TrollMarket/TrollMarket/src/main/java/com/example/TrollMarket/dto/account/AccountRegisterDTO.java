package com.example.TrollMarket.dto.account;

import com.example.TrollMarket.validation.InputMatch;
import com.example.TrollMarket.validation.UniqueUsername;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@InputMatch(message = "password tidak sama",first = "password",second = "confirmPassword")
public class AccountRegisterDTO {
    @NotBlank
    @UniqueUsername(message = "username is already")
    private String username;

    @NotBlank
    private String password;

    private String confirmPassword;

    @NotBlank
    private String role;

    private String name;

    private String address;

    private BigDecimal balance;
}
