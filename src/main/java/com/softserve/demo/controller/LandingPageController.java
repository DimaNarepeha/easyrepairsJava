package com.softserve.demo.controller;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.dto.ProviderInfoDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.impl.FeedbackServiceImpl;
import com.softserve.demo.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("landing-page")
public class LandingPageController {
    private final ProviderFilter filter;
    private final FeedbackServiceImpl feedbackService;

    public LandingPageController(ProviderFilter filter, FeedbackServiceImpl feedbackService) {
        this.filter = filter;
        this.feedbackService = feedbackService;
    }

    @GetMapping("top-four-comment")
    public List<FeedbackGeneralDTO> getTopFourComment() {
        return feedbackService.findTop4ByUpdateDateNotNullOrderByCreatedDate();
    }

    @GetMapping("providers/search-param")
    public Page<ProviderInfoDTO> getProvidersBySearchParam(@RequestParam(defaultValue = Constant.STR_ZERO) int page,
                                                           @RequestParam(defaultValue = Constant.STR_FOUR) int numberOfProvidersOnPage,
                                                           @RequestParam Map<String, String> searchParam) {
        return filter.pageFindByCriteria(page, numberOfProvidersOnPage, searchParam);
    }
}

