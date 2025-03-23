package com.hossync.security.auth.controller;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.hossync.security.auth.entity.UserSignIn;
import com.hossync.security.auth.entity.UserSignUp;
import com.hossync.security.auth.model.SignUpResponse;
import com.hossync.security.auth.service.UserSignUpService;
import com.hossync.security.auth.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse>userSignup(@RequestBody UserSignUp userSignUp){

        SignUpResponse signUpResponse = new SignUpResponse();

        UserSignUp savedUser = userSignUpService.saveUser(userSignUp);
        signUpResponse.setName(savedUser.getName());
        signUpResponse.setEmail(savedUser.getEmail());

        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<String> userSignIn(@RequestBody UserSignIn userSignIn){
        String token = userSignUpService.login(userSignIn);
        return new ResponseEntity<>(token,HttpStatus.CREATED);
    }


    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token){
        String email = jwtService.extractUserEmail(token);
        boolean isValidToken = jwtService.validateToken(token, email);

        return new ResponseEntity<>(isValidToken,HttpStatus.OK);
    }

}
