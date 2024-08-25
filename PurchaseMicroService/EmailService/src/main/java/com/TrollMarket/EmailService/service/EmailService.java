package com.TrollMarket.EmailService.service;

import com.Base.Base.dto.NotificationDto;
import com.TrollMarket.EmailService.entity.Notification;
import com.TrollMarket.EmailService.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final NotificationRepository notificationRepository;

    public EmailService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name.purchase}",
            groupId = "${spring.kafka.consumer.group}"
    )
    public void Email(NotificationDto dto){
        Notification notification = new Notification();
        notification.setEmail(dto.getEmail());
        notification.setBody(dto.getBody());
        notification.setSubject(dto.getSubject());
        notificationRepository.save(notification);
    }


    @KafkaListener(
            topics = "${spring.kafka.topic.name.topup}",
            groupId = "${spring.kafka.consumer.group}"
    )
    public void Emailtopup(NotificationDto dto){
        Notification notification = new Notification();
        notification.setEmail(dto.getEmail());
        notification.setBody(dto.getBody());
        notification.setSubject(dto.getSubject());
        notificationRepository.save(notification);
    }

}
