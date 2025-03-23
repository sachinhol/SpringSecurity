package com.hossync.security.auth.entity;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserSignIn {

    @Email
    private String email;

    @NotBlank
    private String password;

    public UserSignIn() {
    }

    public UserSignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
