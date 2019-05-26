package com.softserve.demo.exceptions;

/**
 * This class represents Exception when some error has occurred while verification
 * or it cannot be performed for some reasons.
 *
 * @author Dmytro Narepekha
 */
public class FailedToVerifyException extends RuntimeException {
    public FailedToVerifyException(String message) {
        super(message);
    }
}
