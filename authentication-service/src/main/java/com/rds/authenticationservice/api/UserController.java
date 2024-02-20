package com.rds.authenticationservice.api;

import com.rds.authenticationservice.dto.UserCredentials;
import com.rds.authenticationservice.model.UserInfo;
import com.rds.authenticationservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public UserInfo signUp(@RequestBody UserInfo userInfo) {
        return userInfoService.saveUser(userInfo);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserCredentials credentials) {
        return userInfoService.loginUser(credentials);
    }



}
