package com.softserve.demo.service.impl;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.dto.MessageDTO;
import com.softserve.demo.model.Chat;
import com.softserve.demo.repository.ChatRepository;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.service.ChatService;
import com.softserve.demo.util.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements ChatService {
    private final
    ChatRepository chatRepository;
private final
CustomerRepository customerRepository;
private final
ProviderRepository providerRepository;
private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, CustomerRepository customerRepository, ProviderRepository providerRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<Chat> getMessagesBySenderAndGetter(Integer customerId,Integer providerId) {
        return chatRepository.findAllBySenderAndGetterId(customerId,providerId);
    }


    @Override
    public void saveMessage(ChatDTO chat) {
        Chat chatE = chatMapper.chatDTOToChat(chat);
        chatE.setCustomerId(customerRepository.findById(chat.getMessageFrom()).get());
        chatE.setProviderId(providerRepository.findById(chat.getMessageTo()).get());
        chatRepository.save(chatE);
    }

    @Override
    public MessageDTO getAllMessages(Integer customerId,Integer providerId) {
        MessageDTO messageDTO = new MessageDTO();
         messageDTO.setMessages(chatRepository.getMessages(customerId,providerId));
         return messageDTO;
    }
}