package com.example.springsec.repository;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:05 PM
 */

import com.example.springsec.entity.DoctorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
    DoctorEntity findByUserName(String userName);

    @Transactional
    @EntityGraph(attributePaths = {"rolesSet"})
    @Query("SELECT d FROM DoctorEntity d WHERE d.userName = :username")
    DoctorEntity findByUserNameWithRoles(@Param("username") String userName);
}
