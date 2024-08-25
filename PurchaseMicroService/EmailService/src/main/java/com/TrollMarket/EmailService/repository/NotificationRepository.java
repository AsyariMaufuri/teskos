package com.TrollMarket.EmailService.repository;

import com.TrollMarket.EmailService.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
