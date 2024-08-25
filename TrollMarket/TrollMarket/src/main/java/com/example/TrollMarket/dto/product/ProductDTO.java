package com.example.TrollMarket.dto.product;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productId;

    private String productName;

    private String category;

    private Boolean discontinue;

    private String priceIDR;
}
