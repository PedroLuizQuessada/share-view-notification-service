package com.example.shareviewnotificationservice.infrastructure.notification.javamailsender.services;

import com.example.shareviewnotificationservice.datasources.NotificationDataSource;
import dtos.NotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("javamailsender")
public class NotificationDataSourceJavaMailSenderImpl implements NotificationDataSource {

    private final JavaMailSender javaMailSender;

    @Value("${email.sender}")
    private String sender;

    @Value("${email.recipient}")
    private String recipient;

    public NotificationDataSourceJavaMailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        mailMessage.setSubject(notificationDto.subject());
        mailMessage.setText(notificationDto.message());
        javaMailSender.send(mailMessage);
    }
}
