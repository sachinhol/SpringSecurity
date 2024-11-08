package com.example.springsec.exception;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:21 PM
 */

import com.example.springsec.model.RestCustomErrorResp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(RegistrationFailed.class)
    public ResponseEntity<RestCustomErrorResp>handleRegistrationFailedException(RegistrationFailed registrationFailed){
        log.info("registration failed : {} ",registrationFailed.getMessage());
        RestCustomErrorResp restCustomErrorResp = new RestCustomErrorResp();
        restCustomErrorResp.setMessage(registrationFailed.getMessage());
        restCustomErrorResp.setStatus("Failed");

        return new ResponseEntity<>(restCustomErrorResp,HttpStatus.BAD_REQUEST);
    }
}
