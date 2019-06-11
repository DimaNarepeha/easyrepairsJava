package com.softserve.demo.service;

import com.softserve.demo.dto.AuthorizationUserDTO;

import java.time.Duration;

public interface AuthorizationUserService {
    AuthorizationUserDTO getUser(String username);

    void updateUser(AuthorizationUserDTO user);

    void checkAttempts(AuthorizationUserDTO userDTO);

    boolean checkLastFailTime(AuthorizationUserDTO userDTO);

    void setDefaultAttemptValue(AuthorizationUserDTO user);

    String getWaitTime(Duration duration);
}
