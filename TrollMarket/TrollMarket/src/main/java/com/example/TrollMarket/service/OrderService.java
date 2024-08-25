package com.example.TrollMarket.service;

import com.example.TrollMarket.dto.order.OrderUpdateInsert;
import com.example.TrollMarket.dto.product.ProductUpdateInsert;

public interface OrderService {
    Boolean checkExistOrder(String username);
    //OrderUpdateInsert getOrderById(Integer invoice,String username);

    void updateInsert(OrderUpdateInsert orderUpdateInsert);
    void purchaseAll(String username);
}
