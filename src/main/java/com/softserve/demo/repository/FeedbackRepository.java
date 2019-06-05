package com.softserve.demo.repository;

import com.softserve.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findTop4ByCreatedDateBefore(LocalDateTime createdDate);
}
