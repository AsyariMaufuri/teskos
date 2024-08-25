package com.example.TrollMarket.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AccountLogin")
public class Account {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Role",nullable = false)
    private String role;

    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "Address",nullable = false)
    private String address;

    @Column(name = "Balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "account")
    private List<Order> orders = new LinkedList<>();

    @OneToMany(mappedBy = "account")
    private List<Product> products = new LinkedList<>();


}
