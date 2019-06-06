package com.softserve.demo.controller;

import com.softserve.demo.service.RecoveryPasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordRecoveryController {

    private static final String SUCCESSFULLY_SENT = "Successfully sent!";

    private final RecoveryPasswordService recoveryPasswordService;

    public PasswordRecoveryController(RecoveryPasswordService recoveryPasswordService) {
        this.recoveryPasswordService = recoveryPasswordService;
    }

    @PostMapping("/recovery")
    public String sendEmailTo(@RequestParam final String email) {
        recoveryPasswordService.passwordRecovery(email);
        return SUCCESSFULLY_SENT;
    }
}
