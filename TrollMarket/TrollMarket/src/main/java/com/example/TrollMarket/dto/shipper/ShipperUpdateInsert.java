package com.example.TrollMarket.dto.shipper;

import jakarta.validation.constraints.NotBlank;
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
public class ShipperUpdateInsert {
    @NotBlank
    private String shipperName;
    @NotNull
    private BigDecimal price;

    private Boolean service;
}
