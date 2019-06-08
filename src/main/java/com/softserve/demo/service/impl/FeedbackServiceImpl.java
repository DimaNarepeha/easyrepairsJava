package com.softserve.demo.service.impl;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.service.FeedbackService;
import com.softserve.demo.util.mappers.FeedbackGeneralMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    final private FeedbackGeneralMapper feedbackMapper;
    final private FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackGeneralMapper feedbackMapper, FeedbackRepository feedbackRepository) {
        this.feedbackMapper = feedbackMapper;
        this.feedbackRepository = feedbackRepository;
    }


    @Override
    public List<FeedbackGeneralDTO> findTop4ByCreatedDateBefore(LocalDateTime createdDate) {
        return feedbackMapper.map(feedbackRepository.findTop4ByCreatedDateBefore(createdDate));
    }
}
