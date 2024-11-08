package com.example.springsec.service;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 7:17 PM
 */

import com.example.springsec.entity.DoctorEntity;
import com.example.springsec.exception.RegistrationFailed;
import com.example.springsec.model.DoctorDetails;
import com.example.springsec.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    public CustomUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load User from DB");
        DoctorEntity doctor = doctorRepository.findByUserName(username);
        if (doctor == null) {
            log.error("Doctor not registered");
            throw new RegistrationFailed("Doctor not registered");
        }

        // Log doctor and roles separately
        log.info("Doctor details: {}", doctor);
        log.info("Doctor's roles: {}", doctor.getRolesSet());

        DoctorDetails doctorDetails = new DoctorDetails();
        BeanUtils.copyProperties(doctor, doctorDetails);

        Set<String> roleNames = doctor.getRolesSet().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());
        doctorDetails.setRoles(roleNames);  // Set the roles in DoctorDetails

        return doctorDetails;
    }
}
