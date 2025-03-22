package com.holsync.security.SpringSecDemo.model;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public class SigninRequest {

    @NonNull
    @Email(message = "Please enter valid email")
    @NotBlank
    private String email;

    @NonNull
    private String password;

    public SigninRequest(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public SigninRequest() {
    }

    @NonNull
    public @Email(message = "Please enter valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NonNull @Email(message = "Please enter valid email") String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
