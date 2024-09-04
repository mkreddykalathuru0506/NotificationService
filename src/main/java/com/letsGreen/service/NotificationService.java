package com.letsGreen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender, KafkaTemplate<String, String> kafkaTemplate) {
        this.javaMailSender = javaMailSender;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void listen(String message) {
        // Example recipient email
        sendNotification("recipient@example.com", "New Notification", message);
    }

    public void sendMessageToKafka(String message) {
        kafkaTemplate.send("notifications", message);
    }
}
