package com.softserve.demo.controller;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("feedback")
@CrossOrigin("*")
public class FeedbackController {

    private final FeedbackService feedbackService;


    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("find-all")
    public List<FeedbackDTO> findAll() {
        return feedbackService.findAll();
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER', 'CUSTOMER')")
    public FeedbackDTO saveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.save(feedbackDTO);
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER', 'CUSTOMER')")
    public FeedbackDTO updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.update(feedbackDTO);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER', 'CUSTOMER')")
    public void deleteFeedback(@PathVariable("id") Integer idFeedback) {
        feedbackService.delete(idFeedback);
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER', 'CUSTOMER')")
    public FeedbackDTO findById(@PathVariable("id") Integer idFeedback) {
        return feedbackService.findById(idFeedback);
    }

    @GetMapping("find-by-user-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER', 'CUSTOMER')")
    public List<FeedbackDTO> findFeedbackByUserId(@PathVariable("id") Integer idFeedback) {
        return feedbackService.findFeedbackByUserId(idFeedback);
    }


}
