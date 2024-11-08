package com.example.springsec.repository;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 8:10 PM
 */

import com.example.springsec.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String role);
}
