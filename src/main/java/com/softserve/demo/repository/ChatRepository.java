package com.softserve.demo.repository;

import com.softserve.demo.model.Chat;
import com.softserve.demo.model.Customer;
import com.softserve.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT chat FROM Chat chat WHERE chat.messageTo.id = :id")
    List<Chat> findAllBySenderAndGetterId(@Param("id")Integer id);


}
