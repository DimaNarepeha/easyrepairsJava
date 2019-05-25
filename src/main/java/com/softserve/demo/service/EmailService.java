/*
 * Open Source 2019
 */
package com.softserve.demo.service;

import com.softserve.demo.model.User;

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
     * @return true if no exception occurred
     */
    boolean sendEmailTo(String addressedTo, String subject, String text);

    /**
     * This method sends verification email
     * to the provided user.
     *
     * @param user receiver of the letter
     * @return true if no exception occurred
     */
    boolean sendVerificationEmailTo(User user);

}
