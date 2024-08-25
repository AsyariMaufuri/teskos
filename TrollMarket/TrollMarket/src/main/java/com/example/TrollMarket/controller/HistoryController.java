package com.example.TrollMarket.controller;

import com.example.TrollMarket.service.AccountService;
import com.example.TrollMarket.service.OrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("history")
public class HistoryController {
    private final OrderDetailService orderDetailService;
    private final AccountService accountService;

    public HistoryController(OrderDetailService service, AccountService accountService) {
        this.orderDetailService = service;
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "")String buyerName, @RequestParam(defaultValue = "")String sellerName){
        var orderDetails = orderDetailService.getAllOrderDetailHistory(buyerName,sellerName,page);
        var hasil = orderDetailService.totalPageHistory(buyerName,sellerName);
        var optionAccountBuyer = accountService.getBuyerAllAccountOption();
        var optionAccountSeller = accountService.getSellerAllAccountOption();
        model.addAttribute("menu","History");
        model.addAttribute("optionAccountBuyer",optionAccountBuyer);
        model.addAttribute("optionAccountSeller",optionAccountSeller);
        model.addAttribute("orderDetails",orderDetails);
        model.addAttribute("totalPages",hasil);
        model.addAttribute("currentPage",page);
        return "history/history-index";
    }
}
