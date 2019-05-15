/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.service.EmailService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implementation of email service.
 *
 * @author Dmytro Narepekha
 */
@Service
public class EmailServiceImpl implements EmailService {
    /**
     * Simple Mail template.
     */
    private static String template;

    /**
     * Email sender from springframework.
     */
    private final JavaMailSender emailSender;

    //reads file with template to string
    static {
        try {
            template = new String(
                                Files.readAllBytes(
                                Paths.get(
                                new ClassPathResource("src\\main\\resources\\email\\emailTemplate.html").getPath())));
        } catch (IOException e) {
            e.printStackTrace();
            template = "";
            //TODO add logging here
        }
    }

    /**
     * constructor which inserts emailSender while initializing.
     *
     * @param emailSender emailSender to be used by email Service
     */
    public EmailServiceImpl(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * This method sends email
     * to the provided address.
     *
     * @param addressedTo      receiver of the letter
     * @param subject subject of the letter
     * @param text    the letter text itself
     * @return true if no exception occurred
     */
    @Override
    public boolean sendEmailTo(final String addressedTo, final String subject, final String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(addressedTo);
            helper.setSubject(subject);
            helper.setText(getMailTemplateWithText(text), true);
            emailSender.send(message);
        } catch (MessagingException e) {
            //TODO add logging here
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method creates an html template for the provided text.
     *
     * @param text text which will be wrapped with html
     * @return html mail message
     */
    private String getMailTemplateWithText(final String text) {
        return String.format(template, text);
    }
}
