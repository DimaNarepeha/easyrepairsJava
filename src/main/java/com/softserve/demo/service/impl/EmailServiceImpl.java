/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Implementation of email service.
 *
 * @author Dmytro Narepekha
 */
@Service
public class EmailServiceImpl implements EmailService {


    /**
     * Email sender from springframework.
     */
    private final JavaMailSender emailSender;
    /**
     * Provided by thymeleaf.
     */
    private final TemplateEngine templateEngine;

    /**
     * constructor which inserts emailSender while initializing.
     *
     * @param emailSender    emailSender to be used by email Service
     * @param templateEngine provided by Thymeleaf
     */
    public EmailServiceImpl(final JavaMailSender emailSender, final TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * This method sends email
     * to the provided address.
     *
     * @param addressedTo receiver of the letter
     * @param subject     subject of the letter
     * @param text        the letter text itself
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
     * @param message text which will be wrapped with html
     * @return html mail message
     */
    private String getMailTemplateWithText(final String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("emailTemplate", context);
    }
}
