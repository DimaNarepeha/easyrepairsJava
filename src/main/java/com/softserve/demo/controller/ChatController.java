package com.softserve.demo.controller;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Chat;
import com.softserve.demo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> typeMessage(
            @RequestBody ChatDTO chat) {
        log.info(chat.toString());
        chatService.saveMessage(chat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}/{providerId}", method=RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable("customerId") Integer customerId,
                                         @PathVariable("providerId") Integer providerId){
        return new ResponseEntity<>(chatService.getMessagesBySenderAndGetter( customerId,providerId),HttpStatus.OK);
    }


}
