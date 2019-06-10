package com.softserve.demo.service.impl;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.model.Chat;
import com.softserve.demo.repository.ChatRepository;
import com.softserve.demo.service.ChatService;
import com.softserve.demo.util.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<Chat> getMessagesBySenderAndGetter(Integer customerId, Integer providerId) {
        return chatRepository.findAllBySenderAndGetterId(customerId, providerId);
    }

    @Override
    public void saveMessage(ChatDTO chatDTO) {
        Chat chat = chatMapper.chatDTOToChat(chatDTO);
        chatRepository.save(chat);
    }

}
