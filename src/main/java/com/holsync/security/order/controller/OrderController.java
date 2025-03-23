package com.holsync.security.order.controller;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.holsync.security.order.entity.Orders;
import com.holsync.security.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders order) {
        Orders savedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
}
