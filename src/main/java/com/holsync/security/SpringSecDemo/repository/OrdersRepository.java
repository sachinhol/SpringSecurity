package com.holsync.security.SpringSecDemo.repository;/*
 * Author: Your Name
 * Date: 22-Mar-25
 * Time: 11:21 AM
 */

import com.holsync.security.SpringSecDemo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    Orders findByEmail(String email);
}
