package com.softserve.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER,
    PROVIDER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}