package com.example.TrollMarket.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateInsert {
    private Integer productId;
    @NotBlank
    private String username;
    @NotBlank
    private String productName;
    @NotBlank
    private String category;
    @NotNull
    private BigDecimal price;

    private String description;
    @NotNull
    private Boolean discontinue;
}
