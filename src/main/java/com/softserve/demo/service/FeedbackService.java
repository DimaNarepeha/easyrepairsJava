package com.softserve.demo.service;

import com.softserve.demo.dto.FeedbackGeneralDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {

    List<FeedbackGeneralDTO> findTop4ByCreatedDateBefore(LocalDateTime createdDate);
}
