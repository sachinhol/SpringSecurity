package com.holsync.security.SpringSecDemo.service.impl;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.model.UserSignUpRequest;
import com.holsync.security.SpringSecDemo.repository.SignUpRepository;
import com.holsync.security.SpringSecDemo.service.UserSignUpServiceInterf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserSignUpServiceImplementation implements UserSignUpServiceInterf {

    private static final Logger logger = LoggerFactory.getLogger(UserSignUpServiceImplementation.class);

    private SignUpRepository signUpRepository;
    private PasswordEncoder passwordEncoder;


    public UserSignUpServiceImplementation(SignUpRepository signUpRepository, PasswordEncoder passwordEncoder){
        this.signUpRepository = signUpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSignUp saveUser(UserSignUpRequest userSignUpRequest) {

        logger.info("User sign up request : {}",userSignUpRequest);

        UserSignUp userSignUp = new UserSignUp();
        userSignUp.setUserId(String.valueOf(UUID.randomUUID()));
        userSignUp.setName(userSignUpRequest.getName());
        userSignUp.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        userSignUp.setEmail(userSignUpRequest.getEmail());
        userSignUp.setRoles("USER");

        UserSignUp signUp = signUpRepository.save(userSignUp);

        logger.info("User saved successfully: {}",userSignUp);

        return signUp;
    }
}
