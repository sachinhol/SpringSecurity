package com.holsync.security.SpringSecDemo.model;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

public class OrderResponse {

    private String userId;
    private String email;
    private long productCode;

    public OrderResponse() {
    }

    public OrderResponse(String userId, String email, long productCode) {
        this.userId = userId;
        this.email = email;
        this.productCode = productCode;
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

    @Override
    public String toString() {
        return "OrderResponse{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
