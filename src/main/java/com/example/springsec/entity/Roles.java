package com.example.springsec.entity;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 7:45 PM
 */

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "rolesSet")
    private Set<DoctorEntity> doctors = new HashSet<>();

    public Roles(String name) {
        this.name = name;
    }
}
