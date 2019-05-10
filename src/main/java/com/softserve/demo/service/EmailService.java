/*
 * Open Source 2019
 */
package com.softserve.demo.service;

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
     * @param to      receiver of the letter
     * @param subject subject of the letter
     * @param text    the letter text itself
     * @return true if no exception occurred
     */
    boolean sendEmailTo(String to, String subject, String text);
}
