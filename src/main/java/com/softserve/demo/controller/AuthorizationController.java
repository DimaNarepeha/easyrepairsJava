package com.softserve.demo.controller;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.service.AuthorizationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class AuthorizationController {

    private final AuthorizationUserService authorizationUserService;

    @Autowired
    public AuthorizationController(AuthorizationUserService authorizationUserService) {
        this.authorizationUserService = authorizationUserService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'PROVIDER')")
    public AuthorizationUserDTO getUser(Principal principal) {
        return authorizationUserService.getUser(principal.getName());
    }
}
