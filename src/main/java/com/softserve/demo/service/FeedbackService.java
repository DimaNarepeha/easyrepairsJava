package com.softserve.demo.service;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import java.util.Calendar;
import java.util.List;

public interface FeedbackService {

    long countByAddressedTo(Integer id);

    List<FeedbackGeneralDTO> findTop4ByCreatedDateBefore(Calendar createdDate);
}
