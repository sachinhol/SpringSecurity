package com.hossync.security.auth.service;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.hossync.security.auth.entity.UserSignIn;
import com.hossync.security.auth.entity.UserSignUp;

public interface UserSignUpService {

    UserSignUp saveUser(UserSignUp userSignUp);

    String login(UserSignIn userSignIn);
}
