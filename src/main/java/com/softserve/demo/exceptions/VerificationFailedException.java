/*
 *Open source 2019
 */
package com.softserve.demo.exceptions;

/**
 * This class represents Exception when some error has occurred while verification
 * or it cannot be performed for some reasons.
 *
 * @author Dmytro Narepekha
 */
public class VerificationFailedException extends RuntimeException {
    public VerificationFailedException(String message) {
        super(message);
    }
}
