package com.example.TrollMarket.controller;

import com.example.TrollMarket.dto.account.AccountRegisterDTO;
import com.example.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final AccountService service;


    public AdminController(AccountService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String register(Model model, @RequestParam(defaultValue = "")String role,AccountRegisterDTO accountRegisterDTO){
        model.addAttribute("role",role);
        model.addAttribute("account",accountRegisterDTO);
        model.addAttribute("menu","Admin");
        return "admin/admin-index";
    }

    @PostMapping("index")
    public String register(@Valid @ModelAttribute("account") AccountRegisterDTO accountRegisterDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/admin-index";
        }else {
            service.register(accountRegisterDTO);
            return "redirect:/admin/index";
        }
    }
}
