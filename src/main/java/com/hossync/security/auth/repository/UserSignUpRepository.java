package com.hossync.security.auth.repository;/*
 * Author: Your Name
 * Date: 23-Mar-25
 * Time: 11:25 AM
 */

import com.hossync.security.auth.entity.UserSignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSignUpRepository extends JpaRepository<UserSignUp, Long> {

    Optional<UserSignUp> findByEmail(String email);
}
