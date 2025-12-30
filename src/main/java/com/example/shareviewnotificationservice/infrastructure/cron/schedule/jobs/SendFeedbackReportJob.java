package com.example.shareviewnotificationservice.infrastructure.cron.schedule.jobs;

import com.example.shareviewnotificationservice.controllers.NotificationController;
import com.example.shareviewnotificationservice.datasources.FeedbackDataSource;
import com.example.shareviewnotificationservice.datasources.NotificationDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("schedule")
public class SendFeedbackReportJob {

    private final NotificationController notificationController;

    public SendFeedbackReportJob(NotificationDataSource notificationDataSource, FeedbackDataSource feedbackDataSource) {
        this.notificationController = new NotificationController(notificationDataSource, feedbackDataSource);
    }

    @Scheduled(cron = "0 30 9 ? * MON", zone = "America/Sao_Paulo")
    public void sendFeedbackReport() {
        log.info("Sending feedback report");
        notificationController.sendFeedbackReportNotificationUseCase();
        log.info("Sent feedback report");
    }

}
