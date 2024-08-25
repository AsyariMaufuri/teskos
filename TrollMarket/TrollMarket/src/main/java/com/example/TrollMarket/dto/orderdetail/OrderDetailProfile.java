package com.example.TrollMarket.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailProfile {
    private LocalDate date;

    private String productName;

    private Integer quantity;

    private String shipperName;

    private String totalPriceIDR;
}
