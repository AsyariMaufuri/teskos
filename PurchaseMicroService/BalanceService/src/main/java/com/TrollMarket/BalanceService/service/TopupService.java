package com.TrollMarket.BalanceService.service;

import com.Base.Base.dto.BalanceDto;
import com.Base.Base.dto.NotificationDto;
import com.TrollMarket.BalanceService.entity.Account;
import com.TrollMarket.BalanceService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopupService {
    @Value("${spring.kafka.topic.name.topup}")
    private String topupTopic;


    private final KafkaTemplate<String,NotificationDto> topup;
    private final AccountRepository repository;


    public TopupService(KafkaTemplate<String, NotificationDto> topup, AccountRepository repository) {
        this.topup = topup;
        this.repository = repository;
    }


    public void topUpBalance(BalanceDto accountTopUp) {
        Account account = repository.getAccountByUsername(accountTopUp.getUsername());
        account.setBalance(account.getBalance().add(accountTopUp.getBalance()));
        repository.save(account);
    }

    public void sendNotificationDto(BalanceDto dto){
        NotificationDto notificationDto = new NotificationDto();
        Account account = repository.getAccountByUsername(dto.getUsername());
        notificationDto.setEmail(account.getName());
        notificationDto.setSubject(String.format("Topup %s", account.getBalance()));
        String body = String.format(" %s, Topup anda Berhasil, terima kasih", account.getName());
        notificationDto.setBody(body);
        topup.send(topupTopic,notificationDto);
    }

}
