package com.example.TrollMarket.rest;

import com.example.TrollMarket.dto.product.ProductUpdateInsert;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;
import com.example.TrollMarket.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchandise")
public class MerchandiseRestController {
    private final ProductService service;

    public MerchandiseRestController(ProductService service) {
        this.service = service;
    }


    @GetMapping("get")
    public ResponseEntity<Object> getProductById(@RequestParam(required = false) Integer productId){
        try {
            ProductUpdateInsert productUpdateInsert = service.getProductByName(productId);
            return ResponseEntity.status(HttpStatus.OK).body(productUpdateInsert);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }
}
