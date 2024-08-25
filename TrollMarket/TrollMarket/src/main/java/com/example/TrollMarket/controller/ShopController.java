package com.example.TrollMarket.controller;

import com.example.TrollMarket.service.AccountService;
import com.example.TrollMarket.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shop")
public class ShopController {
    private final ProductService service;
    private final AccountService accountService;

    public ShopController(ProductService service, AccountService accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "")String productName,@RequestParam(defaultValue = "")String category,@RequestParam(defaultValue = "")String description,@RequestParam(defaultValue = "")String username){
        var products = service.getAllProductByNameCategoryDescription(productName,category,description,page);
        Integer hasil = service.getTotalByNameCategoryDescription(productName,category,description);
        var account = accountService.findAccountByUsername(username);
        model.addAttribute("account",account);
        model.addAttribute("products",products);
        model.addAttribute("deliveryGrid",5);
        model.addAttribute("totalPages",hasil);
        model.addAttribute("currentPage",page);
        model.addAttribute("menu","Shop");
        return "shop/shop-index";
    }
}
