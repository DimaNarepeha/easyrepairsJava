package com.softserve.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
 extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e, WebRequest req) {
        ExceptionResponse exResponse = new ExceptionResponse(e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }
}
