package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 07-Nov-24
 * Time: 5:46 PM
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginToken {

    private String token;
}
