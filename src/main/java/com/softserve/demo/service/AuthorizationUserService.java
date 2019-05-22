package com.softserve.demo.service;

import com.softserve.demo.dto.AuthorizationUserDTO;

public interface AuthorizationUserService {
    AuthorizationUserDTO getUser(String username);
}
