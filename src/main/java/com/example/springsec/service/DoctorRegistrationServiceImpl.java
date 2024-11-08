package com.example.springsec.service;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:12 PM
 */

import com.example.springsec.entity.DoctorEntity;
import com.example.springsec.entity.Roles;
import com.example.springsec.exception.RegistrationFailed;
import com.example.springsec.model.DoctorLoginReq;
import com.example.springsec.model.DoctorsRegisterResponse;
import com.example.springsec.model.DoctorsRegistrationRequest;
import com.example.springsec.model.LoginToken;
import com.example.springsec.repository.DoctorRepository;
import com.example.springsec.repository.RolesRepository;
import com.example.springsec.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DoctorRegistrationServiceImpl implements DoctorRegistration {

    private static final Logger log = LoggerFactory.getLogger(DoctorRegistrationServiceImpl.class);
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public DoctorRegistrationServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public DoctorsRegisterResponse registerDoc(DoctorsRegistrationRequest doctorsRegistrationReq) {
        log.info("Doctor Reg request : {}", doctorsRegistrationReq);

        Set<Roles> roles = new HashSet<>();

        for (String role : doctorsRegistrationReq.getRoles()) {
            Roles rolesFromDb = rolesRepository.findByName(role).orElseGet(() -> rolesRepository.save(new Roles(role)));
            roles.add(rolesFromDb);
        }

        //Encrypt Password
        String encodedPassword = passwordEncoder.encode(doctorsRegistrationReq.getPassword());

        DoctorEntity doctorEntity = DoctorEntity.builder()
                .firstName(doctorsRegistrationReq.firstName)
                .age(doctorsRegistrationReq.age)
                .email(doctorsRegistrationReq.email)
                .userName(doctorsRegistrationReq.userName)
                .password(encodedPassword)
                .rolesSet(roles)
                .build();

        DoctorEntity savedDocEntity = doctorRepository.save(doctorEntity);

        if (StringUtils.isNotBlank(savedDocEntity.getFirstName())) {
            log.info("Doctor Registered: {}", doctorsRegistrationReq.getFirstName());
            return new DoctorsRegisterResponse(savedDocEntity.firstName, "SUCCESS");
        } else {
            log.error("Doctor Registeration failed: {}", doctorsRegistrationReq.getFirstName());
            throw new RegistrationFailed("Registration Failed");
        }

    }

    @Override
    public LoginToken loginDoctor(DoctorLoginReq doctorLoginReq) {
        // Create authentication request
        Authentication authentication  = new UsernamePasswordAuthenticationToken(
                doctorLoginReq.getUserName(), doctorLoginReq.getPassword()
        );
        // Authenticate the user using the AuthenticationManager
        Authentication authenticate = authenticationManager.authenticate(authentication);
//        DoctorEntity doctorEntity = doctorRepository.findByUserName(doctorLoginReq.getUserName());
//        if (null != doctorEntity && StringUtils.isNotBlank(doctorEntity.getUserName())) {
        if(authenticate.isAuthenticated()){
            // Generate JWT token after successful authentication
            String s = jwtUtil.generateToken(doctorLoginReq.getUserName());
            log.info("Doctor registeration found : {}", doctorLoginReq.getUserName());
            return new LoginToken(s);
        } else {
            throw new RegistrationFailed("Doctor Not Register");
        }
    }
}
