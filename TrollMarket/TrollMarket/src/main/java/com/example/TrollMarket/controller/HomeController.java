package com.example.TrollMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("home")
public class HomeController {

    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "")String productName, @RequestParam(defaultValue = "")String category, @RequestParam(defaultValue = "")String description){
        model.addAttribute("menu","Home");
        return "home/home";
    }
}
