package com.softserve.demo.service.impl;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.model.Feedback;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.FeedbackService;
import com.softserve.demo.util.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Illia Chenchak
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.userRepository = userRepository;
    }


    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        feedback.setRating(0.);
        feedback.setAddressedFrom(userRepository.findById(feedback.getAddressedFrom().getId()));
        feedback.setAddressedTo(userRepository.findById(feedback.getAddressedTo().getId()));
        LocalDateTime today = LocalDateTime.now();
        feedback.setCreatedDate(today);
        LocalDateTime endDate = today.plusMinutes(10);
        feedback.setEndDate(endDate);
        feedback.setUpdateDate(null);
        feedback.setUserTo(feedback.getAddressedTo().getUsername() + ", " + feedback.getAddressedTo().getEmail());
        feedback.setUserFrom(feedback.getAddressedFrom().getUsername() + ", " + feedback.getAddressedFrom().getEmail());
        feedbackRepository.save(feedback);
        return feedbackMapper.feedbackToFeedbackDTO(feedback);
    }

    @Override
    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        Feedback newFeedback = feedbackRepository.findById(feedback.getId()).get();
        newFeedback.setAddressedFrom(feedback.getAddressedFrom());
        newFeedback.setAddressedTo(feedback.getAddressedTo());
        newFeedback.setUpdateDate(LocalDateTime.now());
        newFeedback.setComment(feedback.getComment());
        newFeedback.setRating(feedback.getRating());
        return feedbackMapper.feedbackToFeedbackDTO(newFeedback);
    }

    @Override
    public FeedbackDTO findById(Integer id) {
        return feedbackMapper.feedbackToFeedbackDTO(feedbackRepository.findById(id).get());
    }

    @Override
    public List<FeedbackDTO> findAll() {
        return feedbackRepository.findAll().stream().map(
                feedbackMapper::feedbackToFeedbackDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        feedbackRepository.delete(feedbackRepository.findById(id).get());
    }

    @Override
    public List<FeedbackDTO> findFeedbackByUserId(Integer id) {
        return feedbackRepository.findFeedbackByUserId(id).stream().map(
                feedbackMapper::feedbackToFeedbackDTO).collect(Collectors.toList());
    }
}