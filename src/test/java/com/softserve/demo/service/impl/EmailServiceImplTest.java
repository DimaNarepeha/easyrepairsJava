package com.softserve.demo.service.impl;

import com.softserve.demo.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Integration test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailToShouldWork() {
        assertTrue("Email cant be sent, check credentials",
                emailService.sendEmailTo("tom@gmail.com", "test", "test"));
    }
}
