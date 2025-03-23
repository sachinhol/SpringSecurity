package com.hossync.security.auth.security;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.hossync.security.auth.entity.UserSignUp;
import com.hossync.security.auth.repository.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserSignUpRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSignUp userSignUp = signUpRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("No User Found"));


        return new User(
                userSignUp.getEmail(),
                userSignUp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+userSignUp.getRole()))
        );
    }
}
