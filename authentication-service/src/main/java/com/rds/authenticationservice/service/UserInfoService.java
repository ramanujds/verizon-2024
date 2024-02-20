package com.rds.authenticationservice.service;

import com.rds.authenticationservice.dto.UserCredentials;
import com.rds.authenticationservice.model.UserInfo;
import com.rds.authenticationservice.repository.UserInfoRepository;
import com.rds.authenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo saveUser(UserInfo userInfo) {
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodedPassword);
        return userInfoRepo.save(userInfo);
    }


    public String loginUser(UserCredentials userCredentials) {

        UserInfo userInfo = userInfoRepo.findByUsername(userCredentials.username());

        if (userInfo == null) {
            throw new RuntimeException("User not found");
        }



        if(passwordEncoder.matches(userCredentials.password(), userInfo.getPassword())) {

            String token = jwtUtil.generateToken(userInfo.getUsername());
            return token;
        } else {
            throw new RuntimeException("Invalid password");
        }



    }




}
