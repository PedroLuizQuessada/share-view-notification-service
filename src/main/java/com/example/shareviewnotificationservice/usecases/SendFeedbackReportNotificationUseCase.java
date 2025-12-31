package com.example.shareviewnotificationservice.usecases;

import com.example.shareviewnotificationservice.entities.Notification;
import com.example.shareviewnotificationservice.gateways.FeedbackGateway;
import com.example.shareviewnotificationservice.gateways.NotificationGateway;
import com.example.shareviewnotificationservice.mappers.NotificationMapper;
import dtos.ClassDto;
import dtos.CourseDto;
import dtos.FeedbackDto;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SendFeedbackReportNotificationUseCase {

    private final static String SUBJECT = "Relatório de avaliações";
    private final NotificationGateway notificationGateway;
    private final FeedbackGateway feedbackGateway;

    public SendFeedbackReportNotificationUseCase(NotificationGateway notificationGateway, FeedbackGateway feedbackGateway) {
        this.notificationGateway = notificationGateway;
        this.feedbackGateway = feedbackGateway;
    }

    public void execute() {
        List<FeedbackDto> feedbackDtoList = feedbackGateway.findFeedbacksFromWeek();
        Notification notification = new Notification(SUBJECT, buildMessage(feedbackDtoList));
        notificationGateway.sendNotification(NotificationMapper.toDto(notification));
    }

    private String buildMessage(List<FeedbackDto> feedbackDtoList) {
        String message = String.format("Essa semana foram recebidos %d feedbacks.\n", feedbackDtoList.size());
        message = message + String.format("O curso mais avaliado foi %s.\n", findMostEvaluatedCourse(feedbackDtoList));
        message = message + String.format("A média das avaliações foi %s.\n", findAvarageRating(feedbackDtoList));
        message = message + String.format("A 6 dias foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 6));
        message = message + String.format("A 5 dias foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 5));
        message = message + String.format("A 4 dias foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 4));
        message = message + String.format("A 3 dias foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 3));
        message = message + String.format("A 2 dias foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 2));
        message = message + String.format("Ontem foram recebidos %d feedbacks.\n", findNumberOfFeedbacksPerDay(feedbackDtoList, 1));
        message = message + String.format("Hoje foram recebidos %d feedbacks.", findNumberOfFeedbacksPerDay(feedbackDtoList, 0));

        return message;
    }

    private String findMostEvaluatedCourse(List<FeedbackDto> feedbackDtoList) {
        String mostEvaluatedCourse = feedbackDtoList.stream()
                .map(f -> {
                    ClassDto c = (f != null ? f.clazz() : null);
                    CourseDto course = (c != null ? c.course() : null);
                    return (course != null ? course.name() : null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(n -> n, Collectors.counting()),
                        map -> map.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse(null)
                ));
        if (mostEvaluatedCourse == null) {
            mostEvaluatedCourse = "nenhum";
        }
        return mostEvaluatedCourse;
    }

    private double findAvarageRating(List<FeedbackDto> feedbackDtoList) {
        return feedbackDtoList.stream()
                        .mapToInt(f -> f.rating() != null ? f.rating() : 0)
                        .average()
                        .orElse(0.0);
    }

    private long findNumberOfFeedbacksPerDay(List<FeedbackDto> feedbacks, int daysToSubtract) {
        ZoneId zone = ZoneId.systemDefault();

        LocalDate day = LocalDate.now(zone).minusDays(daysToSubtract);
        ZonedDateTime beginDay = day.atStartOfDay(zone);
        ZonedDateTime endDay = day.plusDays(daysToSubtract).atStartOfDay(zone);

        Instant begin = beginDay.toInstant();
        Instant end = endDay.toInstant();

        return feedbacks.stream()
                .map(FeedbackDto::evaluationDate)
                .filter(Objects::nonNull)
                .map(Date::toInstant)
                .filter(inst -> !inst.isBefore(begin) && inst.isBefore(end))
                .count();
    }
}
