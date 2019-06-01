package com.softserve.demo.service.impl;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Chat;
import com.softserve.demo.repository.ChatRepository;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;
@Autowired
    CustomerRepository customerRepository;
@Autowired
    ProviderRepository providerRepository;
    @Override
    public List<Chat> getMessagesBySenderAndGetter(Integer id) {
        return chatRepository.findAllBySenderAndGetterId(id);
    }


    @Override
    public void saveMessage(ChatDTO chat) {

        Chat chatE = new Chat();
        chatE.setMessage(chat.getMessage());
        chatE.setMessageFrom(customerRepository.findById(chat.getMessageFrom()).orElseThrow(()->new NotFoundException("ds")));
        chatE.setMessageTo(providerRepository.findById(chat.getMessageTo()).orElseThrow(()->new NotFoundException("ds")));
        chatRepository.save(chatE);
    }
}
