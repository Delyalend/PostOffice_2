package com.postalSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchPostOfficeException.class)
    protected ResponseEntity<Exception> handlePostOfficeNotFoundException() {
        return new ResponseEntity<>(new Exception("No such post office"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostOfficeAlreadyExists.class)
    protected ResponseEntity<Exception> handlePostOfficeAlreadyExists() {
        return new ResponseEntity<>(new Exception("Such post office already exists"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchPostItemException.class)
    protected ResponseEntity<Exception> handlePostItemNotFoundException() {
        return new ResponseEntity<>(new Exception("No such post item"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnavailableActionAtCurrentStatusException.class)
    protected ResponseEntity<Exception> handleUnavailableActionAtCurrentStatusException() {
        return new ResponseEntity<>(new Exception("Unavailable action in the current status"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectIndexPostOfficeException.class)
    protected ResponseEntity<Exception> handleIncorrectIndexPostOfficeException() {
        return new ResponseEntity<>(new Exception("Invalid post office index"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class Exception {
        private String message;
    }

}
