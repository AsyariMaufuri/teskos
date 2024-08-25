package com.example.TrollMarket.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailMyCartDTO {
    private Integer productId;

    private String productName;

    private Integer invoice;

    private Integer quantity;

    private String shipperName;

    private String sellerName;

    private String totalPrice;
}
