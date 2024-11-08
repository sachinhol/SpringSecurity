package com.example.springsec.service;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 5:11 PM
 */

import com.example.springsec.model.DoctorLoginReq;
import com.example.springsec.model.DoctorsRegisterResponse;
import com.example.springsec.model.DoctorsRegistrationRequest;
import com.example.springsec.model.LoginToken;

public interface DoctorRegistration {

    DoctorsRegisterResponse registerDoc(DoctorsRegistrationRequest doctorsRegistrationReq);

    LoginToken loginDoctor(DoctorLoginReq doctorLoginReq);
}
