package com.letsGreen.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsGreen.service.NotificationService;

@RestController
@RequestMapping("/api/")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public void sendNotification(@RequestBody String message) {
        notificationService.sendMessageToKafka(message);
    }
}
