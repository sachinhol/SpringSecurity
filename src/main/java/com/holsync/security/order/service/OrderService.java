package com.holsync.security.order.service;/*
 * Author: Your Name
 * Date: 23-Mar-25
 * Time: 12:33 AM
 */

import com.holsync.security.order.entity.Orders;

public interface OrderService {
    Orders placeOrder(Orders order);
}
