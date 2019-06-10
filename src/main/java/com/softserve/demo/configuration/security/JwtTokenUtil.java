package com.softserve.demo.configuration.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Component
public class JwtTokenUtil {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
        }
        return authToken;
    }
}