package com.softserve.demo.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

   LocalDateTime time;
   String message;
   String details;

    public ExceptionResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.time = LocalDateTime.now();
    }
}
