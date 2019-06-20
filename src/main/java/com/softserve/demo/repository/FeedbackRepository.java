package com.softserve.demo.repository;

import com.softserve.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("SELECT f from Feedback f where f.addressedFrom.id = :userId and f.updateDate = null")
    List<Feedback> findFeedbackByUserId(@Param("userId") Integer id);

    @Query("SELECT f from Feedback f where f.addressedTo.id = :userId")
    List<Feedback> findActiveFeedbackByUserId(@Param("userId") Integer id);

    List<Feedback> findTop4ByUpdateDateNotNullOrderByCreatedDate();

}
