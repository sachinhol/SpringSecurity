package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 6:20 PM
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorLoginReq {
    private String userName;
    private String password;
}
