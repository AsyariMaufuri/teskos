package com.example.TrollMarket.dto.orderdetail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailUpdateInsert {
    private Integer invoice;

    private Integer productId;
    @NotNull
    private Integer quantity;

    private BigDecimal price;
}
