package com.ganesh.productservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IDNotFoundException.class)
    public ResponseEntity<String> throwIDNotFoundException(IDNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> throwProductNotFoundException(ProductNotFoundException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//    }
}
