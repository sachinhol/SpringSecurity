package com.example.springsec.controller;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 3:24 PM
 */


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vedant")
public class WelcomeContoller {

    @GetMapping("/test")
    public String helloWorld(){
        return "Hello World";
    }
}
