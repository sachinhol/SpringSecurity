package com.holsync.security.SpringSecDemo.service;/*
 * Author: Your Name
 * Date: 22-Mar-25
 * Time: 12:25 PM
 */

import com.holsync.security.SpringSecDemo.entity.UserSignUp;

import java.util.List;

public interface AdminServices {

    List<UserSignUp> getAllUser();
}
