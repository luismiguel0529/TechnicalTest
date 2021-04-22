package com.technicaltest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MutantExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({MutantException.class})
    protected ResponseEntity<Object> handleExceptionMutantException(Exception e, WebRequest webRequest) {
        return handleExceptionInternal(e, "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MutanValidationException.class)
    public Message handleMutantValidationException(MutanValidationException ex) {
        return new Message(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}




