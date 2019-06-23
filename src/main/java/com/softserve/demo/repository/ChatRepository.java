package com.softserve.demo.repository;

import com.softserve.demo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT chat FROM Chat chat WHERE (chat.messageTo.id = :customerId AND chat.messageFrom.id = :providerId) " +
            "or (chat.messageTo.id = :providerId AND chat.messageFrom.id = :customerId) order by chat.sendingTime DESC" )
    List<Chat> findAllBySenderAndGetterId(@Param("customerId")Integer customerId,@Param("providerId")Integer providerId);

    @Query("SELECT chat FROM Chat chat WHERE chat.messageTo.id = :providerId AND chat.messageFrom.id = :customerId and chat.isRead=false order by chat.sendingTime DESC " )
    List<Chat> findUnreadMessages(@Param("customerId")Integer customerId,@Param("providerId")Integer providerId);

    @Query(value="SELECT chat  FROM Chat chat WHERE chat.messageTo.id = :messageTo  and chat.isRead=false  group by chat.messageFrom ")
    List<Chat> findUnreadMessagesForUser(@Param("messageTo")Integer messageTo);

    @Query(value="SELECT chat  FROM Chat chat WHERE chat.messageFrom.id = :messageFrom group by chat.messageTo .id ")
    List<Chat> findMessagesForUser(@Param("messageFrom")Integer messageFrom);

 }
