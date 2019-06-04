package com.softserve.demo.controller;

import com.softserve.demo.dto.FeedbackDTO;
import com.softserve.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(feedbackService.findAll(), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> saveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.save(feedbackDTO), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.update(feedbackDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") Integer id) {
        feedbackService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(feedbackService.findById(id), HttpStatus.OK);
    }

    @GetMapping("find-by-user-id/{id}")
    public ResponseEntity<?> findFeedbackByUserId (@PathVariable("id") Integer id) {
        return new ResponseEntity<>(feedbackService.findFeedbackByUserId(id), HttpStatus.OK);
    }



}
