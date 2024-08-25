package com.TrollMarket.PurchaseService.entity;

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
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;

    @Column(name = "Username",nullable = false)
    private String username;

    @Column(name = "ProductName",nullable = false)
    private String productName;

    @Column(name = "Category",nullable = false)
    private String category;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Description")
    private String description;

    @Column(name = "Discontinue",nullable = false)
    private Boolean discontinue;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new LinkedList<>();
}
