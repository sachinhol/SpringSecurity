package com.example.springsec.entity;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:03 PM
 */

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String firstName;
    public int age;
    public String userName;
    public String password;
    public String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_roles",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> rolesSet = new HashSet<>();
}
