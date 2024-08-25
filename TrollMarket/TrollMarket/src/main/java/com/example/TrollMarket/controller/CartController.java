package com.example.TrollMarket.controller;

import com.example.TrollMarket.service.OrderDetailService;
import com.example.TrollMarket.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {
    private final OrderDetailService orderDetailService;
    private final OrderService orderService;

    public CartController(OrderDetailService orderDetailService, OrderService orderService) {
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
    }


    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "")String username){
        var orderDetails = orderDetailService.getAllOrderDetailMyCart(username,page);
        Integer hasil = orderDetailService.totalPage(username);
        model.addAttribute("menu","My Cart");
        model.addAttribute("orderDetails",orderDetails);
        model.addAttribute("deliveryGrid",5);
        model.addAttribute("totalPages",hasil);
        model.addAttribute("currentPage",page);
        return "cart/cart-index";
    }

    @GetMapping("remove")
    public String remove(String username, Integer invoice, Integer productId){
        orderDetailService.remove( username,  invoice,  productId);
        return "redirect:/cart/index?username="+username;
    }

/*    @GetMapping("purchase")
    public String purchaseAll(String username){
        orderService.purchaseAll(username);

        return "redirect:/cart/index";
    }*/



}
