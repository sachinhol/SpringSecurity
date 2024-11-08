package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:10 PM
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorsRegisterResponse {
    public String name;
    public String status;
}
