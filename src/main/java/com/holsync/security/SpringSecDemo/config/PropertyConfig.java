package com.holsync.security.SpringSecDemo.config;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String expiration;

    public String getSecretKey() {
        return secretKey;
    }

    public String getExpiration() {
        return expiration;
    }
}
