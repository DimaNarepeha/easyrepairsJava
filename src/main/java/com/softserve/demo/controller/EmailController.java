package com.softserve.demo.controller;

import com.softserve.demo.dto.EmailDTO;
import com.softserve.demo.service.EmailService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public String sendEmailTo(@RequestBody @Valid final EmailDTO emailDTO) {
        emailService.sendEmailTo(emailDTO.getAddressedTo(), emailDTO.getSubject(), emailDTO.getText());
        return "Successfully sent!";
    }
}
