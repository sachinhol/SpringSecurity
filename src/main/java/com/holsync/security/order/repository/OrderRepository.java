package com.holsync.security.order.repository;/*
 * Author: Your Name
 * Date: 23-Mar-25
 * Time: 12:32 AM
 */

import com.holsync.security.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
