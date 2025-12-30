package com.example.shareviewnotificationservice.controllers;

import com.example.shareviewnotificationservice.datasources.FeedbackDataSource;
import com.example.shareviewnotificationservice.datasources.NotificationDataSource;
import com.example.shareviewnotificationservice.gateways.FeedbackGateway;
import com.example.shareviewnotificationservice.gateways.NotificationGateway;
import com.example.shareviewnotificationservice.usecases.SendBadFeedbackNotificationUseCase;
import com.example.shareviewnotificationservice.usecases.SendFeedbackReportNotificationUseCase;
import dtos.requests.CreateBadFeedbackNotificationRequest;

public class NotificationController {

    private final NotificationDataSource notificationDataSource;
    private final FeedbackDataSource feedbackDataSource;

    public NotificationController(NotificationDataSource notificationDataSource, FeedbackDataSource feedbackDataSource) {
        this.notificationDataSource = notificationDataSource;
        this.feedbackDataSource = feedbackDataSource;
    }

    public void sendBadFeedbackNotificationUseCase(CreateBadFeedbackNotificationRequest createBadFeedbackNotificationRequest) {
        NotificationGateway notificationGateway = new NotificationGateway(notificationDataSource);
        SendBadFeedbackNotificationUseCase useCase = new SendBadFeedbackNotificationUseCase(notificationGateway);
        useCase.execute(createBadFeedbackNotificationRequest);
    }

    public void sendFeedbackReportNotificationUseCase() {
        NotificationGateway notificationGateway = new NotificationGateway(notificationDataSource);
        FeedbackGateway feedbackGateway = new FeedbackGateway(feedbackDataSource);
        SendFeedbackReportNotificationUseCase useCase = new SendFeedbackReportNotificationUseCase(notificationGateway, feedbackGateway);
        useCase.execute();
    }

}
