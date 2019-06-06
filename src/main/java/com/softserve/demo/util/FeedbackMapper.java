package com.softserve.demo.util;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.model.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDTO feedbackToFeedbackDTO(Feedback feedback);

    Feedback feedbackDTOToFeedback(FeedbackDTO feedbackDTO);
}
