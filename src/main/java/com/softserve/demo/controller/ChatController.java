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
    @Autowired
    ChatService chatService;


    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody ChatDTO chat) {
        log.info(chat.toString());
        chatService.saveMessage(chat);
       //chatService.getMessagesBySenderAndGetter(1).forEach((Chat c)->log.info(c.toString()));
        return new ResponseEntity<>("Finally!!!!",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCustomer(){
        return new ResponseEntity<>(chatService.getMessagesBySenderAndGetter(7,1),HttpStatus.OK);
    }


}
