package com.example.TrollMarket.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailHistory {
    private LocalDate date;

    private String sellerName;

    private String buyerName;

    private String productName;

    private Integer quantity;

    private String shipperName;

    private String totalPriceIDR;
}
