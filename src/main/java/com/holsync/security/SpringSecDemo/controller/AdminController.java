package com.holsync.security.SpringSecDemo.controller;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/getalluser")
    public ResponseEntity<List<UserSignUp>> getAllUser(){
        List<UserSignUp> allUser = adminServices.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }


}
