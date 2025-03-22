package com.holsync.security.SpringSecDemo.securities;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.repository.SignUpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    SignUpRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.info("loadUserByUsername emai : {} ", email);

        UserSignUp userSignUp = signUpRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not foun"));

        return new User(
                userSignUp.getEmail(),
                userSignUp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+userSignUp.getRoles()))
        );
    }
}
