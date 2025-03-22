package com.holsync.security.SpringSecDemo.controller;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.Orders;
import com.holsync.security.SpringSecDemo.model.OrderRequest;
import com.holsync.security.SpringSecDemo.model.OrderResponse;
import com.holsync.security.SpringSecDemo.service.OrderServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderServiceInterf orderServiceInterf;

    @PostMapping("/createorder")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = new OrderResponse();
        Orders orders = orderServiceInterf.saveOrder(orderRequest);
        orderResponse.setEmail(orders.getEmail());
        orderResponse.setUserId(orders.getUserId());
        orderResponse.setProductCode(orders.getProductCode());

        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);


    }
}
