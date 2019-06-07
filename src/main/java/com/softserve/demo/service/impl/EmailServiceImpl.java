/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.model.User;
import com.softserve.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
@Slf4j
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
     * Constant subject for verification email.
     */
    private static final String SUBJECT_ACTIVATION_CODE = "Activation code";
    /**
     * Address of our host to be inserted in email messages.
     */
    @Value("${hostlink}")
    private String HOSTLINK;

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
     * This method creates an html template for the provided user.
     *
     * @param user to which template will be generated
     * @return html mail message
     */
    private String getVerificationTemplate(final User user) {
        String activationLink = HOSTLINK + "/login/verify/" + user.getActivationCode();
        Context context = new Context();
        context.setVariable("username", user.getUsername());
        context.setVariable("activationLink", activationLink);
        return templateEngine.process("verificationEmailTemplate", context);
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

    /**
     * Sends email with provided generated HTML template.
     *
     * @param addressedTo       address of the receiver
     * @param subject           of the letter
     * @param generatedTemplate template of the body of the letter
     */
    private void sendEmailWithTemplate(final String addressedTo, final String subject, final String generatedTemplate) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(addressedTo);
            helper.setSubject(subject);
            helper.setText(generatedTemplate, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            log.error("Unable to send email to [{}]!", addressedTo, e);
        }
    }

    /**
     * This method sends email
     * to the provided address.
     *
     * @param addressedTo receiver of the letter
     * @param subject     subject of the letter
     * @param text        the letter text itself
     */
    @Override
    @Async
    public void sendEmailTo(final String addressedTo, final String subject, final String text) {
        sendEmailWithTemplate(addressedTo, subject, getMailTemplateWithText(text));
    }

    /**
     * This method sends Verification letter
     * to the provided address.
     *
     * @param user from which all information is gathered
     */
    @Override
    @Async
    public void sendVerificationEmailTo(final User user) {
        sendEmailWithTemplate(user.getEmail(), SUBJECT_ACTIVATION_CODE, getVerificationTemplate(user));
    }
}
