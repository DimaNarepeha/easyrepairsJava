package com.softserve.demo.controller;

import com.softserve.demo.configuration.security.JwtTokenUtil;
import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.service.AuthorizationUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
public class AuthorizationController {


    private final TokenStore tokenStore;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthorizationUserService authorizationUserService;

    public AuthorizationController(AuthorizationUserService authorizationUserService, TokenStore tokenStore,
                                   JwtTokenUtil jwtTokenUtil) {
        this.authorizationUserService = authorizationUserService;
        this.tokenStore = tokenStore;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_PROVIDER')")
    public AuthorizationUserDTO getUser(Principal principal) {
        return authorizationUserService.getUser(principal.getName());
    }

    @GetMapping("/logmeout")
    public void logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(jwtTokenUtil.getAccessToken(request));
            tokenStore.removeAccessToken(oAuth2AccessToken);
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
