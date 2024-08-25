package com.Api.ApiGateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("AccountLogin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column("Username")
    private String username;

    @Column("Password")
    private String password;

    @Column("Role")
    private String role;

    @Column("Name")
    private String name;

    @Column("Address")
    private String address;

    @Column("Balance")
    private BigDecimal balance;
}
