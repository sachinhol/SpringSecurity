package com.holsync.security.order.exception;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ProductCallException extends RuntimeException {

    private final String message;
    private final HttpStatus status;


    public ProductCallException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
