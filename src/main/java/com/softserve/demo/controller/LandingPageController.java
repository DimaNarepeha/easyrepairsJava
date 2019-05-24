package com.softserve.demo.controller;


import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.dto.ProviderCriteriaDTO;
import com.softserve.demo.dto.ServiceProviderInfoDTO;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.service.impl.FeedbackServiceImpl;
import com.softserve.demo.util.FeedbackGeneralMapper;
import com.softserve.demo.util.ProviderCriteriaMapper;
import com.softserve.demo.util.ProviderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("landing-page")
@ResponseBody
public class LandingPageController {
    private final ProviderFilter filter;
    private final ProviderInfoMapper providerInfoMapper;
    private final ProviderCriteriaMapper providerCriteriaMapper;
    private final FeedbackServiceImpl feedbackService;


    @Autowired
    public LandingPageController(ProviderFilter filter, ProviderInfoMapper providerInfoMapper, FeedbackGeneralMapper feedbackMapper, ProviderCriteriaMapper providerCriteriaMapper, FeedbackServiceImpl feedbackService) {
        this.filter = filter;
        this.providerInfoMapper = providerInfoMapper;
        this.providerCriteriaMapper = providerCriteriaMapper;
        this.feedbackService = feedbackService;
    }


    @GetMapping("all-approved")
    public ResponseEntity<List<ServiceProviderInfoDTO>> getAllApproved() {
        return new ResponseEntity<>(providerInfoMapper.map(filter.findAllApproved()), HttpStatus.OK);
    }

    @GetMapping("top-comment")
    public ResponseEntity<List<FeedbackGeneralDTO>> getTopComment() {
        return new ResponseEntity<>(feedbackService.findTop4ByCreatedDateBefore(Calendar.getInstance()), HttpStatus.OK);
    }

    @PostMapping("filter")
    public ResponseEntity<?> filter(@RequestBody ProviderCriteriaDTO criteria) {
          return new ResponseEntity<>(filter.findByListServices(providerCriteriaMapper.providerCriteriaDTOToProviderCriteria(criteria)), HttpStatus.OK);
    }


}

