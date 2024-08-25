package com.TrollMarket.PurchaseService.service;

import com.Base.Base.dto.NotificationDto;
import com.Base.Base.dto.PurchaseDto;
import com.TrollMarket.PurchaseService.entity.Account;
import com.TrollMarket.PurchaseService.entity.Order;
import com.TrollMarket.PurchaseService.entity.OrderDetail;
import com.TrollMarket.PurchaseService.repository.AccountRepository;
import com.TrollMarket.PurchaseService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class PurchaseService {
    @Value("${spring.kafka.topic.name.purchase}")
    private String purchaseTopic;

    private final KafkaTemplate<String, NotificationDto> purchase;
    private final AccountRepository accountRepository;
    private final OrderRepository repository;


    public PurchaseService(KafkaTemplate<String, NotificationDto> purchase, AccountRepository accountRepository, OrderRepository repository) {
        this.purchase = purchase;
        this.accountRepository = accountRepository;
        this.repository = repository;
    }


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

    public void sendNotificationDto(PurchaseDto dto){
        NotificationDto notificationDto = new NotificationDto();
        Account account = accountRepository.getAccountByUsername(dto.getUsername());
        notificationDto.setEmail(account.getName());
        notificationDto.setSubject(String.format("[%s] Order TrollMarket", repository.orderInCartByInvoice(account.getUsername()).getInvoice()));
        List<OrderDetail> order = repository.orderInCartByInvoice(account.getUsername()).getOrderDetails();
        String product = "";
        for (OrderDetail orderDetail : order){
            product = product +" " + orderDetail.getProduct().getProductName();
        }
        String body = String.format(" %s anda Berhasil di order, terima kasih", product);
        notificationDto.setBody(body);
        purchase.send(purchaseTopic,notificationDto);
    }

}
