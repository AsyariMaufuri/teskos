package com.example.TrollMarket.service;

import com.example.TrollMarket.dto.orderdetail.OrderDetailDTO;
import com.example.TrollMarket.dto.orderdetail.OrderDetailHistory;
import com.example.TrollMarket.dto.orderdetail.OrderDetailMyCartDTO;
import com.example.TrollMarket.dto.orderdetail.OrderDetailProfile;
import com.example.TrollMarket.entitiy.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailMyCartDTO> getAllOrderDetailMyCart(String username,Integer page);
    List<OrderDetailHistory> getAllOrderDetailHistory(String buyerUsername,String sellerUsername,Integer page);
    List<OrderDetailProfile> getAllOrderDetailProfile(String username,Integer page);

    Integer totalPage(String username);
    Integer totalPageHistory(String buyerUsername,String sellerUsername);
    Integer totalPageProfile(String username);
    void remove(String username, Integer orderDetailId, Integer productId);
}
