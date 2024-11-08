package com.example.springsec.controller;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 3:30 PM
 */

import com.example.springsec.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vedant")
public class ProductController {

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct(){
        Product p1 = new Product("iPhone",125000,1);
        Product p2 = new Product("Macbook",185000,1);
        List<Product>listOfProduct =  List.of(p1,p2);
        return new ResponseEntity<>(listOfProduct, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
