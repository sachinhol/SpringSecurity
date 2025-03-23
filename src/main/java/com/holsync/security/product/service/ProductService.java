package com.holsync.security.product.service;/*
 * Author: Your Name
 * Date: 22-Mar-25
 * Time: 10:50 PM
 */

import com.holsync.security.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    boolean updatedStock(Long productId, int quantity);
}
