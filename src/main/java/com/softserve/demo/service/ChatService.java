package com.softserve.demo.service;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.dto.MessageDTO;
import com.softserve.demo.model.Chat;

import java.util.List;

public interface ChatService {


    List<Chat>  getMessagesBySenderAndGetter(Integer customerId,Integer providerId);

    void saveMessage(ChatDTO chat);

    MessageDTO getAllMessages(Integer customerId, Integer providerId);
}
