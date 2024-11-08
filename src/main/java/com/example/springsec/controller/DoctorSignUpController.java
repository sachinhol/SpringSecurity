package com.example.springsec.controller;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:02 PM
 */

import com.example.springsec.model.DoctorLoginReq;
import com.example.springsec.model.DoctorsRegisterResponse;
import com.example.springsec.model.DoctorsRegistrationRequest;
import com.example.springsec.model.LoginToken;
import com.example.springsec.service.DoctorRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vedant")
@Slf4j
public class DoctorSignUpController {

    @Autowired
    DoctorRegistration doctorRegistration;

    @PostMapping("/register")
    public ResponseEntity<DoctorsRegisterResponse> registerDoctor(@RequestBody DoctorsRegistrationRequest doctorsRegistrationReq){
        log.info("Doctor Reg request recieved");
        DoctorsRegisterResponse doctorsRegisterResponse =doctorRegistration.registerDoc(doctorsRegistrationReq);
        return new ResponseEntity<>(doctorsRegisterResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginToken> loginDoctor(@RequestBody DoctorLoginReq doctorLoginReq){
        log.info("Recieved Login request for : {} ", doctorLoginReq.getUserName());
         LoginToken loginTokenResponse=doctorRegistration.loginDoctor(doctorLoginReq);
         return new ResponseEntity<LoginToken>(loginTokenResponse,HttpStatus.OK);
    }
}
