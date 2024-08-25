package com.example.TrollMarket.controller;

import com.example.TrollMarket.dto.account.AccountRegisterDTO;
import com.example.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("login")
    public String login(@RequestParam(required = false) Boolean error, Model model){
        model.addAttribute("error", error);
        return "account/login-index";
    }
    @GetMapping("register")
    public String register(Model model,@RequestParam(defaultValue = "")String role, AccountRegisterDTO accountRegisterDTO){
        model.addAttribute("role",role);
        model.addAttribute("account",accountRegisterDTO);
        return "account/login-form";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("account") AccountRegisterDTO accountRegisterDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "account/login-form";
        }else {
            service.register(accountRegisterDTO);
            return "redirect:login";
        }

    }

    @GetMapping("access-denied")
    public String denied(){
        return "account/access-denied";
    }
}
