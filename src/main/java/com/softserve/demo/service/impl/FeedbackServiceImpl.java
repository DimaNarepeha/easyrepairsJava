package com.softserve.demo.service.impl;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.model.Feedback;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.FeedbackService;
import com.softserve.demo.util.FeedbackGeneralMapper;
import com.softserve.demo.util.FeedbackMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    private final UserRepository userRepository;

    private final FeedbackGeneralMapper feedbackGeneralMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, UserRepository userRepository, FeedbackGeneralMapper feedbackGeneralMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.userRepository = userRepository;
        this.feedbackGeneralMapper = feedbackGeneralMapper;
    }


    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        feedback.setRating(0.);
        feedback.setAddressedFrom(userRepository.findById(feedback.getAddressedFrom().getId()));
        feedback.setAddressedTo(userRepository.findById(feedback.getAddressedTo().getId()));
        feedback.setCreatedDate(LocalDateTime.now());
        feedbackRepository.save(feedback);
        return feedbackMapper.feedbackToFeedbackDTO(feedback);
    }

    @Override
    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        Feedback newFeedback = feedbackRepository.findById(feedback.getId()).get();
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

    @Override
    public List<FeedbackGeneralDTO> findTop4ByCreatedDateBefore(LocalDateTime createdDate) {
        return feedbackGeneralMapper.map(feedbackRepository.findTop4ByCreatedDateBefore(createdDate));
    }

}
