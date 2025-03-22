package com.holsync.security.SpringSecDemo.service;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;
import com.holsync.security.SpringSecDemo.model.UserSignUpRequest;

public interface UserSignUpServiceInterf {

    UserSignUp saveUser(UserSignUpRequest userSignUpRequest);
}
