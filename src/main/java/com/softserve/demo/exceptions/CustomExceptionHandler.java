package com.softserve.demo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class, VerificationFailedException.class})
    public ResponseEntity<?> handleNotFoundException(Exception e, WebRequest req) {
        ExceptionResponse exResponse = new ExceptionResponse(e.getMessage(), req.getDescription(false));
        log.warn("Not found", e);
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<?> handleAlreadyExistException(Exception e, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), req.getDescription(false));
        log.warn(e.getMessage() + "already exist");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
