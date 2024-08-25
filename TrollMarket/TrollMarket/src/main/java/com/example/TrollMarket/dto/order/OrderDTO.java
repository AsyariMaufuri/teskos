package com.example.TrollMarket.dto.order;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Integer invoice;

    private String username;

    private LocalDate orderDate;

    private Boolean inCart;

    private BigDecimal shipperPrice;
}
