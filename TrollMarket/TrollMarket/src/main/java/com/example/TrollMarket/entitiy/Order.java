package com.example.TrollMarket.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Invoice")
    private Integer invoice;

    @Column(name = "Username",nullable = false)
    private String username;

    @Column(name = "OrderDate",nullable = false)
    private LocalDate orderDate;

    @Column(name = "InCart",nullable = false)
    private Boolean inCart;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new LinkedList<>();
}
