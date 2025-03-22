package com.holsync.security.SpringSecDemo.service.impl;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.repository.SignUpRepository;
import com.holsync.security.SpringSecDemo.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public List<UserSignUp> getAllUser() {

        List<UserSignUp> allSingedUpUsers = signUpRepository.findAll();

        return allSingedUpUsers;
    }
}
