package com.TrollMarket.PurchaseService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailId",nullable = false)
    private Integer orderDetailId;

    @Column(name = "Invoice",nullable = false)
    private Integer invoice;

    @Column(name = "ProductId",nullable = false)
    private Integer productId;

    @Column(name = "Quantity",nullable = false)
    private Integer quantity;

    @Column(name = "Price",nullable = false)
    private BigDecimal price;

    @Column(name = "ShipperName",nullable = false)
    private String shipperName;

    @Column(name = "ShipperPrice",nullable = false)
    private BigDecimal shipperPrice;

    @Column(name = "Note")
    private String  note;

    @ManyToOne
    @JoinColumn(name = "Invoice", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ShipperName", insertable = false, updatable = false)
    private Shipper shipper;

}
