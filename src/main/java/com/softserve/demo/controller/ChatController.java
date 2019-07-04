package com.softserve.demo.controller;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.model.Chat;
import com.softserve.demo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("message")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public void typeMessage(
            @RequestBody ChatDTO chat) {
        log.info(chat.toString());
        chatService.saveMessage(chat);
    }

    @RequestMapping(value = "/{customerId}/{providerId}", method = RequestMethod.GET)
    public List<ChatDTO> getCustomer(@PathVariable("customerId") Integer customerId,
                                     @PathVariable("providerId") Integer providerId) {
        return chatService.getMessagesBySenderAndGetter(customerId, providerId);
    }

    @RequestMapping(value = "unread/{customerId}/{providerId}", method = RequestMethod.GET)
    public List<ChatDTO> getUnreadMessages(@PathVariable("customerId") Integer customerId,
                                           @PathVariable("providerId") Integer providerId) {
        return chatService.getUnreadMessages(customerId, providerId);
    }

    @RequestMapping(value = "read/{customerId}/{providerId}", method = RequestMethod.POST)
    public void readMessages(@PathVariable("customerId") Integer customerId,
                             @PathVariable("providerId") Integer providerId) {
        chatService.readMessages(customerId, providerId);
    }

    @RequestMapping(value = "getUnreadForUser/{messageTo}", method = RequestMethod.GET)
    public List<ChatDTO> readMessages(@PathVariable("messageTo") Integer messageTo) {
        return chatService.getUreadMessagesForUser(messageTo);
    }
}
