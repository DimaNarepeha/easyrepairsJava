/*
 * Open Source 2019
 */
package com.softserve.demo.service;

import com.softserve.demo.model.User;

import java.io.File;

/**
 * Simple email service
 * to send emails for your purpose.
 *
 * @author Dmytro Narepekha
 */
public interface EmailService {
    /**
     * This method sends email
     * to the provided address.
     *
     * @param addressedTo receiver of the letter
     * @param subject     subject of the letter
     * @param text        the letter text itself
     */
    void sendEmailTo(String addressedTo, String subject, String text);

    /**
     * This method sends verification email
     * to the provided user.
     *
     * @param user receiver of the letter
     */
    void sendVerificationEmailTo(User user);

    /**
     * This method sends email
     * to the provided address.
     *
     * @param addressedTo receiver of the letter
     * @param subject     subject of the letter
     * @param text        the letter text itself
     * @param file        the file which you want to attach
     */
    void sendEmailWithFile(String addressedTo, String subject, String text, File file);

}
