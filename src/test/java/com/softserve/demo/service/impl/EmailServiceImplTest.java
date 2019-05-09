package com.softserve.demo.service.impl;

import com.softserve.demo.service.EmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.test.util.AssertionErrors.assertTrue;


@Component
public class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailToShouldWork() {
        assertTrue("Email cant be sent, check credentials",
                emailService.sendEmailTo("tom@gmail.com", "test", "test"));
    }
}
