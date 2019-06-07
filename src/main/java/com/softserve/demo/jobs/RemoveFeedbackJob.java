package com.softserve.demo.jobs;

import com.softserve.demo.service.FeedbackService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class RemoveFeedbackJob {

    private final FeedbackService feedbackService;


    public RemoveFeedbackJob(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkEndDateInFeedBack() {
        feedbackService.findAll().stream().forEach(feedback -> {
            if ((feedback.getUpdateDate() == null) && (feedback.getEndDate().isBefore(LocalDateTime.now()))) {
                feedbackService.delete(feedback.getId());
            }
        });
    }

}