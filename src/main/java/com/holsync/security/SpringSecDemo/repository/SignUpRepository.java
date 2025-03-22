package com.holsync.security.SpringSecDemo.repository;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRepository extends JpaRepository<UserSignUp,Long> {

   Optional<UserSignUp> findByEmail(String email);
}
