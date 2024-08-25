package com.example.TrollMarket.dto.shipper;

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
public class ShipperDTO {
    private String shipperName;

    private BigDecimal price;

    private String priceIDR;

    private Boolean service;
}
