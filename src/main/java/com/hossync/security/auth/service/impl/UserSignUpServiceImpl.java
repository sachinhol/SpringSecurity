package com.hossync.security.auth.service.impl;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.hossync.security.auth.entity.Role;
import com.hossync.security.auth.entity.UserSignIn;
import com.hossync.security.auth.entity.UserSignUp;
import com.hossync.security.auth.repository.UserSignUpRepository;
import com.hossync.security.auth.security.CustomUserDetailService;
import com.hossync.security.auth.service.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpServiceImpl implements UserSignUpService {

    @Autowired
    private UserSignUpRepository signUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserSignUp saveUser(UserSignUp userSignUp) {

        userSignUp.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
        userSignUp.setRole("ROLE_" + Role.USER);
        return signUpRepository.save(userSignUp);
    }

    @Override
    public String login(UserSignIn userSignIn) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignIn.getEmail(), userSignIn.getPassword())
        );

        if(!authenticate.isAuthenticated()){
            throw new RuntimeException("User Not Found");
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(userSignIn.getEmail());


        return "Bearer "+jwtService.generateToken(userDetails.getUsername());
    }
}
