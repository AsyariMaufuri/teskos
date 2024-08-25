package com.example.TrollMarket.dto.orderdetail;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Integer invoice;

    private Integer productId;

    private Integer quantity;

    private BigDecimal price;
}
