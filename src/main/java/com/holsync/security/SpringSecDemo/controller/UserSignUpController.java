package com.holsync.security.SpringSecDemo.controller;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.model.SignInSuccessResponse;
import com.holsync.security.SpringSecDemo.model.SigninRequest;
import com.holsync.security.SpringSecDemo.model.UserSignUpRequest;
import com.holsync.security.SpringSecDemo.model.UserSignUpResponse;
import com.holsync.security.SpringSecDemo.securities.JwtService;
import com.holsync.security.SpringSecDemo.service.UserSignUpServiceInterf;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class UserSignUpController {

    @Autowired
    private UserSignUpServiceInterf userSignUpService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registeruser")
    public ResponseEntity<UserSignUpResponse>createUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        UserSignUp userSignUp = userSignUpService.saveUser(userSignUpRequest);
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setName(userSignUp.getName());
        userSignUpResponse.setEmail(userSignUp.getEmail());

        return new ResponseEntity<>(userSignUpResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<SignInSuccessResponse> loginUser(@RequestBody @Valid SigninRequest signinRequest){

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtService.generateToken(signinRequest.getEmail());
        SignInSuccessResponse signInSuccessResponse = new SignInSuccessResponse();
        signInSuccessResponse.setToken("Bearer "+token);
        return new ResponseEntity<>(signInSuccessResponse,HttpStatus.OK);
    }
}
