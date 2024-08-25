package com.example.TrollMarket.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderUpdateInsert {
    @NotBlank
    private String username;
    @NotBlank
    private String shipperName;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    private String note;

    private String infoBalance;


}
