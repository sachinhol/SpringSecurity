package com.holsync.security.product.exception;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(String message) {
        super(message);
    }
}
