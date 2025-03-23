package com.holsync.security.order.controller;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */


import com.holsync.security.order.client.FeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor feignClientInterceptor(){
        return new FeignClientInterceptor();
    }
}
