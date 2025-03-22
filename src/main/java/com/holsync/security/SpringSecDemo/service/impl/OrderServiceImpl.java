package com.holsync.security.SpringSecDemo.service.impl;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holsync.security.SpringSecDemo.entity.Orders;
import com.holsync.security.SpringSecDemo.model.OrderRequest;
import com.holsync.security.SpringSecDemo.repository.OrdersRepository;
import com.holsync.security.SpringSecDemo.service.OrderServiceInterf;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderServiceInterf {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Orders saveOrder(OrderRequest orderRequest) {

        logger.info("Order Request : {}", orderRequest);

        Orders orders = objectMapper.convertValue(orderRequest,Orders.class);
        logger.info("before saving Order : {}", orders);
        Orders savedOrder = ordersRepository.save(orders);

        logger.info("Saved Order : {}", savedOrder);


        return savedOrder;
    }
}
