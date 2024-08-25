package com.example.TrollMarket.dto.account;

import com.example.TrollMarket.entitiy.Order;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;

    private String password;

    private String role;

    private String name;

    private String address;

    private BigDecimal balance;

    private List<Order> orders = new LinkedList<>();

}
