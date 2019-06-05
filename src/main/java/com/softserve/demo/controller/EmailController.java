package com.softserve.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.softserve.demo.dto.EmailDTO;
import com.softserve.demo.service.EmailService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final ObjectMapper JsonMapper;

    public EmailController(final EmailService emailService, final ObjectMapper jsonMapper) {
        this.emailService = emailService;
        JsonMapper = jsonMapper;
    }

    @PostMapping
    public ObjectNode sendEmailTo(@RequestBody @Valid final EmailDTO emailDTO) {
        emailService.sendEmailTo(emailDTO.getAddressedTo(), emailDTO.getSubject(), emailDTO.getText());
        ObjectNode jsonResponse = JsonMapper.createObjectNode();
        jsonResponse.put("message", "Successfully sent!");
        return jsonResponse;
    }
}
