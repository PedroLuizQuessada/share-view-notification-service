package com.example.shareviewnotificationservice.datasources;

import dtos.FeedbackDto;

import java.util.List;

public interface FeedbackDataSource {
    List<FeedbackDto> findFeedbacksFromWeek();
}
