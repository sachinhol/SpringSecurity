package com.example.springsec.exception;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:19 PM
 */

public class RegistrationFailed extends RuntimeException{

    public RegistrationFailed(String message) {
        super(message);
    }
}
