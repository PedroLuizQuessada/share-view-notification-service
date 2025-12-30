package com.example.shareviewnotificationservice.usecases;

import com.example.shareviewnotificationservice.entities.Notification;
import com.example.shareviewnotificationservice.gateways.NotificationGateway;
import com.example.shareviewnotificationservice.mappers.NotificationMapper;
import dtos.requests.CreateBadFeedbackNotificationRequest;

public class SendBadFeedbackNotificationUseCase {

    private final static String SUBJECT = "Avaliação negativa recebida";
    private final static String MESSAGE = "O aluno %s atribuiu o seguinte feedback ao curso %s:\nNota %d.\nComentários: %s.";
    private final NotificationGateway notificationGateway;

    public SendBadFeedbackNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public void execute(CreateBadFeedbackNotificationRequest createBadFeedbackNotificationRequest) {
        Notification notification = new Notification(SUBJECT, buildMessage(createBadFeedbackNotificationRequest));
        notificationGateway.sendNotification(NotificationMapper.toDto(notification));
    }

    private String buildMessage(CreateBadFeedbackNotificationRequest createBadFeedbackNotificationRequest) {
        return String.format(MESSAGE,
                createBadFeedbackNotificationRequest.student(), createBadFeedbackNotificationRequest.course(),
                createBadFeedbackNotificationRequest.rating(), createBadFeedbackNotificationRequest.description());
    }

}
