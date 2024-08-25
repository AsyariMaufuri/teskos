package com.example.TrollMarket.controller;

import com.example.TrollMarket.dto.product.ProductUpdateInsert;
import com.example.TrollMarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("merchandise")
public class MerchandiseController {
    private final ProductService service;

    public MerchandiseController(ProductService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "")String username ,@RequestParam(defaultValue = "1") Integer page){
        var products = service.getAllProduct(page,username);
        Integer hasil = service.totalPage(username);
        model.addAttribute("products",products);
        model.addAttribute("deliveryGrid",5);
        model.addAttribute("totalPages",hasil);
        model.addAttribute("currentPage",page);
        model.addAttribute("menu","Merchandise");
        return "merchandise/merchandise-index";
    }

    @GetMapping("form")
    public String form(Model model, @RequestParam(defaultValue = "0")Integer productId){
        var productById = service.getProductByName(productId);
        model.addAttribute("productById",productById);
        return "merchandise/merchandise-form";
    }

    @PostMapping("form")
    public String form(@Valid @ModelAttribute("productById") ProductUpdateInsert productUpdateInsert, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "merchandise/merchandise-form";
        }else {
            service.updateInsert(productUpdateInsert);
            return "redirect:/merchandise/index";
        }

    }

    @GetMapping("delete")
    public String delete(Integer productId){
        service.delete(productId);
        return "redirect:/merchandise/index";
    }

    @GetMapping("discontinue")
    public String serviceStop(Integer productId){
        service.discontinue(productId);
        return "redirect:/merchandise/index";
    }


}
