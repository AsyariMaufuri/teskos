package com.example.TrollMarket.service.implement;

import com.example.TrollMarket.dto.orderdetail.OrderDetailHistory;
import com.example.TrollMarket.dto.orderdetail.OrderDetailMyCartDTO;
import com.example.TrollMarket.dto.orderdetail.OrderDetailProfile;
import com.example.TrollMarket.entitiy.OrderDetail;
import com.example.TrollMarket.helper.Helper;
import com.example.TrollMarket.repository.OrderDetailRepository;
import com.example.TrollMarket.repository.OrderRepository;
import com.example.TrollMarket.repository.ProductRepository;
import com.example.TrollMarket.service.OrderDetailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
@Service
public class OrderDetailServiceImplement implements OrderDetailService {
    private final OrderDetailRepository repository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderDetailServiceImplement(OrderDetailRepository repository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDetailMyCartDTO> getAllOrderDetailMyCart(String username,Integer page) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("invoice"));
        List<OrderDetail> orderDetails = repository.getOrderDetailInCartByUsername(username,pageable);
        List<OrderDetailMyCartDTO> gridOrderDetail = new LinkedList<>();
        for (var ordd: orderDetails){
            OrderDetailMyCartDTO orderDetailMyCartDTO = new OrderDetailMyCartDTO();
            orderDetailMyCartDTO.setInvoice(ordd.getOrderDetailId());
            orderDetailMyCartDTO.setProductId(ordd.getProductId());
            orderDetailMyCartDTO.setQuantity(ordd.getQuantity());
            orderDetailMyCartDTO.setProductName(productRepository.getProductById(ordd.getProductId()).getProductName() );
            orderDetailMyCartDTO.setSellerName(ordd.getProduct().getAccount().getName());
            orderDetailMyCartDTO.setShipperName(ordd.getShipperName());
            orderDetailMyCartDTO.setTotalPrice(Helper.FormatIDR(ordd.getPrice().multiply(new BigDecimal(ordd.getQuantity()))) );
            gridOrderDetail.add(orderDetailMyCartDTO);
        }
        return gridOrderDetail;

    }

    @Override
    public List<OrderDetailHistory> getAllOrderDetailHistory(String buyerUsername, String sellerUsername,Integer page) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("invoice"));
        List<OrderDetail> orderDetails = repository.getOrderDetailsHistory(pageable,buyerUsername,sellerUsername);
        List<OrderDetailHistory> historyList = new LinkedList<>();

        for (var orderDetail : orderDetails){
            OrderDetailHistory orderDetailHistory = new OrderDetailHistory();
            orderDetailHistory.setDate(orderDetail.getOrder().getOrderDate());
            orderDetailHistory.setQuantity(orderDetail.getQuantity());
            orderDetailHistory.setProductName(orderDetail.getProduct().getProductName());
            orderDetailHistory.setSellerName(orderDetail.getProduct().getAccount().getName());
            orderDetailHistory.setShipperName(orderDetail.getShipperName());
            orderDetailHistory.setBuyerName(orderDetail.getOrder().getUsername());
            orderDetailHistory.setTotalPriceIDR(Helper.FormatIDR(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity()))));
            historyList.add(orderDetailHistory);
        }


        return historyList;
    }

    @Override
    public List<OrderDetailProfile> getAllOrderDetailProfile(String username,Integer page) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("invoice"));
        List<OrderDetail> orderDetails = repository.getOrderDetailsProfile(pageable,username);
        List<OrderDetailProfile> profileList = new LinkedList<>();

        for (var orderDetail : orderDetails){
            OrderDetailProfile orderDetailProfile = new OrderDetailProfile();
            orderDetailProfile.setDate(orderDetail.getOrder().getOrderDate());
            orderDetailProfile.setQuantity(orderDetail.getQuantity());
            orderDetailProfile.setProductName(orderDetail.getProduct().getProductName());
            orderDetailProfile.setShipperName(orderDetail.getShipperName());
            orderDetailProfile.setTotalPriceIDR(Helper.FormatIDR(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity()))));
            profileList.add(orderDetailProfile);
        }


        return profileList;
    }

    @Override
    public Integer totalPage(String username){
        int total = repository.getTotal(username);
        int hasil = 0;
        if(total %5==0 ){
            hasil = total/5;
        }else {
            int sisa = total%5;
            hasil = (total-sisa)/5;
            hasil = hasil + 1;
        }
        return hasil;
    }

    @Override
    public Integer totalPageHistory(String buyerUsername, String sellerUsername) {
        int total = repository.getTotalHistory(buyerUsername, sellerUsername);
        int hasil = 0;
        if(total %5==0 ){
            hasil = total/5;
        }else {
            int sisa = total%5;
            hasil = (total-sisa)/5;
            hasil = hasil + 1;
        }
        return hasil;
    }

    @Override
    public Integer totalPageProfile(String username) {
        int total = repository.getTotalProfile(username);
        int hasil = 0;
        if(total %5==0 ){
            hasil = total/5;
        }else {
            int sisa = total%5;
            hasil = (total-sisa)/5;
            hasil = hasil + 1;
        }
        return hasil;
    }

    @Override
    public void remove(String username, Integer orderDetailId, Integer productId) {
        OrderDetail orderDetail = repository.getOrderDetailByInvoiceUsernameProductId(username, orderDetailId, productId);
        repository.delete(orderDetail);
    }


}
