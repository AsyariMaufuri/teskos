package com.example.TrollMarket.service.implement;

import com.example.TrollMarket.dto.order.OrderUpdateInsert;
import com.example.TrollMarket.entitiy.Account;
import com.example.TrollMarket.entitiy.Order;
import com.example.TrollMarket.entitiy.OrderDetail;
import com.example.TrollMarket.repository.*;
import com.example.TrollMarket.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImplement implements OrderService {
    private final OrderRepository repository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final ShipperRepository shipperRepository;
    private final AccountRepository accountRepository;

    public OrderServiceImplement(OrderRepository repository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository, ShipperRepository shipperRepository, AccountRepository accountRepository) {
        this.repository = repository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.shipperRepository = shipperRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public Boolean checkExistOrder(String username) {
        Integer exist =  repository.checkExistOrder(username);
        if (exist > 0){
            return true;
        }else {
            return false;
        }
    }

/*    @Override
    public OrderUpdateInsert getOrderById(Integer invoice,String username) {
        OrderUpdateInsert orderUpdateInsert = new OrderUpdateInsert();
        invoice = invoice == null ? 0 : invoice;
        username = username == null ? "" : username;
        if ((invoice != 0) && (!username.equals(""))){
            Order order = repository.orderInCartByInvoice(username,invoice);
            orderUpdateInsert.setOrderDate(order.getOrderDate());
            orderUpdateInsert.setShipperName(order.getShipperName());
            orderUpdateInsert.setInCart(order.getInCart());
            orderUpdateInsert.setInvoice(order.getInvoice());
            orderUpdateInsert.setShipperPrice(order.getShipperPrice());
            orderUpdateInsert.setUsername(order.getUsername());
        }
        return orderUpdateInsert;
    }*/


    @Override
    public void updateInsert(OrderUpdateInsert orderUpdateInsert) {
        Order order = new Order();

        if (checkExistOrder(orderUpdateInsert.getUsername())){
            order = repository.orderInCartByInvoice(orderUpdateInsert.getUsername());

        }else {
            order.setOrderDate(LocalDate.now());
            order.setUsername(orderUpdateInsert.getUsername());
            order.setInCart(true);
        }
        order = repository.save(order);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setInvoice(order.getInvoice());
        orderDetail.setProductId(orderUpdateInsert.getProductId());
        orderDetail.setPrice(productRepository.getProductById(orderUpdateInsert.getProductId()).getPrice());
        orderDetail.setShipperName(orderUpdateInsert.getShipperName());
        orderDetail.setQuantity(orderUpdateInsert.getQuantity());
        orderDetail.setShipperPrice(shipperRepository.getShipperByName(orderUpdateInsert.getShipperName()).getPrice());
        orderDetail.setNote(orderUpdateInsert.getNote());

        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void purchaseAll(String username) {
        Order order = repository.orderInCartByInvoice(username);
        Account buyer = order.getAccount();
        BigDecimal balanceBuyyer = buyer.getBalance();
        List<OrderDetail> orderDetails = order.getOrderDetails();

        for (var orderdetail : orderDetails){
            Account seller = orderdetail.getProduct().getAccount();
            BigDecimal balanceSeller = seller.getBalance();
            BigDecimal totalPrice = orderdetail.getPrice().multiply(new BigDecimal(orderdetail.getQuantity())).add(orderdetail.getShipper().getPrice());
            balanceBuyyer = balanceBuyyer.subtract(totalPrice);
            balanceSeller = balanceSeller.add(totalPrice);
            seller.setBalance(balanceSeller);
            buyer.setBalance(balanceBuyyer);
            accountRepository.save(seller);
            accountRepository.save(buyer);
        }


        order.setInCart(false);
        repository.save(order);
    }
}
