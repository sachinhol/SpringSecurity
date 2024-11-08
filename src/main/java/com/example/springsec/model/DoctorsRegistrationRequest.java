package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:07 PM
 */

import com.example.springsec.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorsRegistrationRequest {

    public String firstName;
    public int age;
    public String userName;
    public String password;
    public String email;
    private Set<String> roles;
}
