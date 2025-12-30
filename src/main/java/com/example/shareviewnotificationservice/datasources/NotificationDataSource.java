package com.example.shareviewnotificationservice.datasources;

import dtos.NotificationDto;

public interface NotificationDataSource {
    void sendNotification(NotificationDto notificationDto);
}
