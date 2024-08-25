package com.example.TrollMarket.rest;

import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;
import com.example.TrollMarket.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentRestController {
    private final ShipperService service;

    public ShipmentRestController(ShipperService service) {
        this.service = service;
    }

    @GetMapping("get")
    public ResponseEntity<Object> getShipper(@RequestParam(required = false) String shipperName){
        try {
            ShipperUpdateInsert shipperUpdateInsert = service.getShipperByName(shipperName);
            return ResponseEntity.status(HttpStatus.OK).body(shipperUpdateInsert);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }


    @PostMapping("post")
    public ResponseEntity<Object> postShipper(@Valid @RequestBody ShipperUpdateInsert shipperUpdateInsert, BindingResult bindingResult){
        try {
            if  (bindingResult.hasErrors()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult.getAllErrors());
            }else {
                service.updateInsert(shipperUpdateInsert);
                return ResponseEntity.status(HttpStatus.OK).body("sukses");
            }

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }

}
