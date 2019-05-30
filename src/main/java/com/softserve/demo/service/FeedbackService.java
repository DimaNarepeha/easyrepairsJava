package com.softserve.demo.service;

import com.softserve.demo.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback save (Feedback feedback);

    Feedback update (Feedback feedback);

    Feedback findById (Integer id);

    List<Feedback> findAll ();

    void delete (Integer id);
}
