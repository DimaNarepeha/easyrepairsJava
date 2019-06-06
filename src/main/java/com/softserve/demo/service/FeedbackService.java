package com.softserve.demo.service;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.dto.FeedbackGeneralDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {

    FeedbackDTO save(FeedbackDTO feedbackDTO);

    FeedbackDTO update(FeedbackDTO feedbackDTO);

    FeedbackDTO findById(Integer id);

    List<FeedbackDTO> findAll();

    void delete(Integer id);

    List<FeedbackDTO> findFeedbackByUserId(Integer id);

    List<FeedbackGeneralDTO> findTop4ByCreatedDateBefore(LocalDateTime createdDate);
}
