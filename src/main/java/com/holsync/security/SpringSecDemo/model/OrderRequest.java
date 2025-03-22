package com.holsync.security.SpringSecDemo.model;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

public class OrderRequest {

    private String userId;
    private String email;
    private long productCode;
    private String product;
    private double price;
    private String paymentMode;

    public OrderRequest() {
    }

    public OrderRequest(String userId, String email, long productCode, String product, double price, String paymentMode) {
        this.userId = userId;
        this.email = email;
        this.productCode = productCode;
        this.product = product;
        this.price = price;
        this.paymentMode = paymentMode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", productCode='" + productCode + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}
