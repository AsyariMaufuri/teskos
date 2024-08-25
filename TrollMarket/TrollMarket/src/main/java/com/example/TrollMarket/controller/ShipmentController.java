package com.example.TrollMarket.controller;

import com.example.TrollMarket.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("shipment")
public class ShipmentController {
    private final ShipperService service;

    public ShipmentController(ShipperService service) {
        this.service = service;
    }

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page){
        var shippers = service.getAllShipper(page);
        Integer hasil = service.totalPage();
        model.addAttribute("shippers",shippers);
        model.addAttribute("deliveryGrid",5);
        model.addAttribute("totalPages",hasil);
        model.addAttribute("currentPage",page);
        model.addAttribute("menu","Shipment");
        return "shipment/shipment-index";
    }

    @GetMapping("delete")
    public String delete(Model model,String shipperName){
        service.delete(shipperName);
        return "redirect:/shipment/index";
    }

    @GetMapping("service")
    public String serviceStop(Model model,String shipperName){
        service.serviceStop(shipperName);
        return "redirect:/shipment/index";
    }

}
