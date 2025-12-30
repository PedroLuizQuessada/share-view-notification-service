package com.example.shareviewnotificationservice.gateways;

import com.example.shareviewnotificationservice.datasources.FeedbackDataSource;
import dtos.FeedbackDto;

import java.util.List;

public class FeedbackGateway {

    private final FeedbackDataSource feedbackDataSource;

    public FeedbackGateway(FeedbackDataSource feedbackDataSource) {
        this.feedbackDataSource = feedbackDataSource;
    }

    public List<FeedbackDto> findFeedbacksFromWeek() {
        return feedbackDataSource.findFeedbacksFromWeek();
    }
}
