package com.holsync.security.order.client;/*
 * Author: Your Name
 * Date: 23-Mar-25
 * Time: 12:51 AM
 */

import com.holsync.security.order.controller.FeignConfig;
import com.holsync.security.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "API-GATEWAY",configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/api/product/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id);

    @PutMapping("/api/product/updatestock/{productId}/{quantity}")
    ResponseEntity<Boolean>updateStock(@PathVariable Long productId, @PathVariable int quantity);
}
