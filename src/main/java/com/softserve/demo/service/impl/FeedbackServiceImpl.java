package com.softserve.demo.service.impl;

import com.softserve.demo.model.Feedback;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Illia Chenchak
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    @Override
    public Feedback save(Feedback feedback) {
        feedback.setAvailabel(false);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(Feedback feedback) {
        Feedback newFeedback = feedbackRepository.findById(feedback.getId()).get();
        newFeedback.setAddressedFrom(feedback.getAddressedFrom());
        newFeedback.setAddressedTo(feedback.getAddressedTo());
        newFeedback.setAvailabel(false);
        newFeedback.setComment(feedback.getComment());
        newFeedback.setCreatedDate(feedback.getCreatedDate());
        newFeedback.setEndDate(feedback.getEndDate());
        newFeedback.setRating(feedback.getRating());
        return newFeedback;
    }

    @Override
    public Feedback findById(Integer id) {
        return feedbackRepository.findById(id).get();
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        feedbackRepository.delete(feedbackRepository.findById(id).get());
    }
}
