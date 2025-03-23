package com.holsync.security.order.service.impl;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.holsync.security.order.client.ProductClient;
import com.holsync.security.order.dto.ProductResponse;
import com.holsync.security.order.entity.Orders;
import com.holsync.security.order.exception.ProductCallException;
import com.holsync.security.order.repository.OrderRepository;
import com.holsync.security.order.service.OrderService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public Orders placeOrder(Orders order) {
        try {
        logger.info("Get Product for: {}", order.getProductId());

        ResponseEntity<ProductResponse> productResponse;

        productResponse = productClient.getProductById(order.getProductId());

        logger.info("Recived Product : {}", productResponse);

        if (productResponse.getStatusCode().is2xxSuccessful() && productResponse.getBody() != null) {
            ProductResponse product = productResponse.getBody();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderDate(LocalDateTime.now());
            order.setTotalPrice(product.getPrice() * order.getQuantity());
        } else {
            logger.warn("⚠️ Product not found or API Gateway fallback triggered!");
            throw new ProductCallException("⚠️ Product not found or API Gateway fallback triggered!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
            productClient.updateStock(order.getProductId(), order.getQuantity());
            return orderRepository.save(order);

        } catch (FeignException e) {

            String fallbackResponse = e.contentUTF8();
            HttpStatus statusCode = HttpStatus.valueOf(e.status());
            logger.error("fallbackResponse : {}, statusCode : {}", fallbackResponse, statusCode);
            throw new ProductCallException(fallbackResponse, statusCode);
        }
    }

}
