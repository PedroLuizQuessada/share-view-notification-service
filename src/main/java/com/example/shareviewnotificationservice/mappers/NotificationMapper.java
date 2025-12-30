package com.example.shareviewnotificationservice.mappers;

import com.example.shareviewnotificationservice.entities.Notification;
import dtos.NotificationDto;

public class NotificationMapper {

    private NotificationMapper() {}

    public static NotificationDto toDto(Notification notification) {
        return  new NotificationDto(notification.getSubject(), notification.getMessage());
    }

}
