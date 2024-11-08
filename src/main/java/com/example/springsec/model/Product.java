package com.example.springsec.model;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 3:31 PM
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;
    private int price;
    private int quantity;
}
