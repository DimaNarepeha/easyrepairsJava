package com.softserve.demo.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime time;
    private String message;
    private String details;

    public ExceptionResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.time = LocalDateTime.now();
    }
}
