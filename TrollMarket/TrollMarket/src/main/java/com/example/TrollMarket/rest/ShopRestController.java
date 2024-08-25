package com.example.TrollMarket.rest;

import com.example.TrollMarket.dto.ShipperOptionDTO;
import com.example.TrollMarket.dto.order.OrderUpdateInsert;
import com.example.TrollMarket.service.OrderService;
import com.example.TrollMarket.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
    private final ShipperService shipperService;
    private final OrderService orderService;

    public ShopRestController(ShipperService shipperService, OrderService orderService) {
        this.shipperService = shipperService;
        this.orderService = orderService;
    }

    @GetMapping("get")
    public ResponseEntity<Object> getShipper(){
        try {
           List<ShipperOptionDTO> optionDTOList = shipperService.getAllShipperServiceOn();
            return ResponseEntity.status(HttpStatus.OK).body(optionDTOList);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }

    @PostMapping("post")
    public ResponseEntity<Object> postAddToCart(@Valid @RequestBody OrderUpdateInsert orderUpdateInsert, BindingResult bindingResult){
        try {
            if  (bindingResult.hasErrors()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult.getAllErrors());
            }else {
                orderService.updateInsert(orderUpdateInsert);
                return ResponseEntity.status(HttpStatus.OK).body("sukses");
            }

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }

}
