package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:22 PM
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestCustomErrorResp {

    private String status;
    private String message;
}
