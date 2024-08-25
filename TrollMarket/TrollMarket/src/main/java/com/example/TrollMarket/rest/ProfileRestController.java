package com.example.TrollMarket.rest;

import com.example.TrollMarket.dto.account.AccountTopUp;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;
import com.example.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {
    private final AccountService service;

    public ProfileRestController(AccountService service) {
        this.service = service;
    }

    @PostMapping("post")
    public ResponseEntity<Object> postBalance(@Valid @RequestBody AccountTopUp accountTopUp, BindingResult bindingResult){
        try {
            if  (bindingResult.hasErrors()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult.getAllErrors());
            }else {
                service.topUpBalance(accountTopUp);
                return ResponseEntity.status(HttpStatus.OK).body("sukses");
            }

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is runtime error");
        }
    }
}
