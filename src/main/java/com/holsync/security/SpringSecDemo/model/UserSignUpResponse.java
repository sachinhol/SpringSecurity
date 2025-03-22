package com.holsync.security.SpringSecDemo.model;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

public class UserSignUpResponse {

    public String name;
    public String email;

    public UserSignUpResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserSignUpResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
