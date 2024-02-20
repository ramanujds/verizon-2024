package com.rds.authenticationservice.service;

import com.rds.authenticationservice.model.UserInfo;
import com.rds.authenticationservice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.findByUsername(username);
        return new UserDetailsImpl(userInfo.getUsername(), userInfo.getPassword(), userInfo.getRole());

    }
}
