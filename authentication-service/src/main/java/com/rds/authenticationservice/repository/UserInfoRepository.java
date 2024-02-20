package com.rds.authenticationservice.repository;

import com.rds.authenticationservice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUsername(String username);

}
