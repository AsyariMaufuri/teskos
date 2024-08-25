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
@Table(name = "Shippers")
public class Shipper {
    @Id
    @Column(name = "ShipperName")
    private String shipperName;

    @Column(name = "Price",nullable = false)
    private BigDecimal price;

    @Column(name = "Service",nullable = false)
    private Boolean service;

    @OneToMany(mappedBy = "shipper")
    private List<OrderDetail> orderDetails = new LinkedList<>();
}
