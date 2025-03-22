package com.holsync.security.SpringSecDemo.model;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

public class SignInSuccessResponse {

    private String token;

    public SignInSuccessResponse(String token) {
        this.token = token;
    }

    public SignInSuccessResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
