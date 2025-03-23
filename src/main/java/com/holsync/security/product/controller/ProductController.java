package com.holsync.security.product.controller;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.product.entity.Product;
import com.holsync.security.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // ✅ Get all products
    @GetMapping("getall")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // ✅ Delete product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }

    @PutMapping("/updatestock/{productId}/{quantity}")
    public ResponseEntity<Boolean>updateStock(@PathVariable Long productId, @PathVariable int quantity){
        boolean isStockUpdated = productService.updatedStock(productId, quantity);
        return new ResponseEntity<>(isStockUpdated,HttpStatus.OK);
    }
}
