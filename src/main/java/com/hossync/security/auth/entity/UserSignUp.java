package com.hossync.security.auth.entity;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

@Entity
public class UserSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Please enter valid password")
    private String password;


    private String role;

    public UserSignUp() {
    }

    public UserSignUp(String password, String email, @NonNull String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @Email(message = "Enter valid email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Enter valid email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Please enter valid password") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Please enter valid password") String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
