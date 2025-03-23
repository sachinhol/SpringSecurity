package com.holsync.security.order.exception;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductCallException.class)
    public ResponseEntity<String> handleProductCallException(ProductCallException exception){

        return new ResponseEntity<>( exception.getMessage(),exception.getStatus());
    }
}
