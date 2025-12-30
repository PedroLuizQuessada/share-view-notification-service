package com.example.shareviewnotificationservice.gateways;

import com.example.shareviewnotificationservice.datasources.NotificationDataSource;
import dtos.NotificationDto;

public class NotificationGateway {

    private final NotificationDataSource notificationDataSource;

    public NotificationGateway(NotificationDataSource notificationDataSource) {
        this.notificationDataSource = notificationDataSource;
    }

    public void sendNotification(NotificationDto notificationDto) {
        notificationDataSource.sendNotification(notificationDto);
    }

}
