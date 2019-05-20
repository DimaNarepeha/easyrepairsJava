package com.softserve.demo.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
