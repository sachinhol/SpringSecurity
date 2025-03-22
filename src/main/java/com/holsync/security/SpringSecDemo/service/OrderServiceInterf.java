package com.holsync.security.SpringSecDemo.service;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */


import com.holsync.security.SpringSecDemo.entity.Orders;
import com.holsync.security.SpringSecDemo.model.OrderRequest;

public interface OrderServiceInterf {

    Orders saveOrder(OrderRequest orderRequest);

}
