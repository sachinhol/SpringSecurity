package com.holsync.security.product.service.impl;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.product.entity.Product;
import com.holsync.security.product.exception.InsufficientStockException;
import com.holsync.security.product.repository.ProductRepository;
import com.holsync.security.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProduct = productRepository.findAll();
        return allProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("No Product Found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean updatedStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("No Product Found"));

        if(product.getStock()<quantity) {
            throw new InsufficientStockException("No quantity present");
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        return true;
    }
}
