package com.example.TrollMarket.controller;

import com.example.TrollMarket.service.AccountService;
import com.example.TrollMarket.service.OrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("profile")
public class ProfileController {
    private final AccountService accountService;
    private final OrderDetailService orderDetailService;

    public ProfileController(AccountService accountService, OrderDetailService orderDetailService) {
        this.accountService = accountService;
        this.orderDetailService = orderDetailService;

    }

    @GetMapping("index")
    public String form(Model model, @RequestParam(defaultValue = "")String username,@RequestParam(defaultValue = "1") Integer page){

        var account = accountService.findAccountByUsername(username);
        var orderDetails = orderDetailService.getAllOrderDetailProfile(username,page);
        model.addAttribute("orderDetails",orderDetails);
        model.addAttribute("account",account);
        model.addAttribute("menu","Profile");
        return "profile/profile-index";
    }
}
