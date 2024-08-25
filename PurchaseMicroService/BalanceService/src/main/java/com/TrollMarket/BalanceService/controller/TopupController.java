package com.TrollMarket.BalanceService.controller;

import com.Base.Base.dto.BalanceDto;
import com.TrollMarket.BalanceService.service.TopupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class TopupController {
    private final TopupService service;

    public TopupController(TopupService service) {
        this.service = service;
    }

    @PostMapping("post")
    public ResponseEntity<Object> postBalance(@RequestBody BalanceDto accountTopUp){
        try {
                service.topUpBalance(accountTopUp);
                service.sendNotificationDto(accountTopUp);
                return ResponseEntity.status(HttpStatus.OK).body("sukses");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
