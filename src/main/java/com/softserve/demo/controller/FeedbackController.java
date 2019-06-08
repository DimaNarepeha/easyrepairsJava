package com.softserve.demo.controller;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Illia Chenchak
 */
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
    public FeedbackDTO saveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.save(feedbackDTO);
    }

    @PutMapping("update")
    public FeedbackDTO updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.update(feedbackDTO);
    }

    @DeleteMapping("delete/{id}")
    public void deleteFeedback(@PathVariable("id") Integer idFeedback) {
        feedbackService.delete(idFeedback);
    }

    @GetMapping("find-by-id/{id}")
    public FeedbackDTO findById(@PathVariable("id") Integer idFeedback) {
        return feedbackService.findById(idFeedback);
    }

    @GetMapping("find-by-user-id/{id}")
    public List<FeedbackDTO> findFeedbackByUserId(@PathVariable("id") Integer idFeedback) {
        return feedbackService.findFeedbackByUserId(idFeedback);
    }


}
