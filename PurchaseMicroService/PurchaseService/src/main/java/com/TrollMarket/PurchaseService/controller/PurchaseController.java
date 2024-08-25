package com.TrollMarket.PurchaseService.controller;

import com.Base.Base.dto.PurchaseDto;
import com.TrollMarket.PurchaseService.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> postPurchaseEmail(@RequestBody PurchaseDto dto){
        try{
            service.sendNotificationDto(dto);
            service.purchaseAll(dto.getUsername());
            return ResponseEntity.ok("sukses");
        }catch (Exception exception){
            return ResponseEntity.internalServerError().body( exception.getMessage());
        }
    }
}
