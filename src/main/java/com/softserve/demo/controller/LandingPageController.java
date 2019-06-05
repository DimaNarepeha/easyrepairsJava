package com.softserve.demo.controller;


import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.impl.FeedbackServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("landing-page")
@ResponseBody
public class LandingPageController {
    private final ProviderFilter filter;
    private final FeedbackServiceImpl feedbackService;

    public LandingPageController(ProviderFilter filter, FeedbackServiceImpl feedbackService) {
        this.filter = filter;
        this.feedbackService = feedbackService;
    }

    @GetMapping("top-four-comment")
    public ResponseEntity<List<FeedbackGeneralDTO>> getTopFourComment() {
        return new ResponseEntity<>(feedbackService.findTop4ByCreatedDateBefore(LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("providers/search-param")
    public Page<?> getProvidersBySearchParam(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam int numberOfProvidersOnPage,
                                  @RequestParam Map<String, String> searchParam) {
        return filter.pageFindByCriteria(page, numberOfProvidersOnPage, searchParam);
    }
}

