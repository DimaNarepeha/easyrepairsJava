package com.softserve.demo.configuration.security;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.service.AuthorizationUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, AuthenticationManager {

    private static final int BLOCK_TIME = 3;

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthorizationUserService authorizationUserService;

    public CustomAuthenticationProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                        BCryptPasswordEncoder passwordEncoder,
                                        AuthorizationUserService authorizationUserService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authorizationUserService = authorizationUserService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        AuthorizationUserDTO user = authorizationUserService.getUser(login);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            authorizationUserService.checkAttempts(user);
        }

        if (authorizationUserService.checkLastFailTime(user)) {
            Duration duration = Duration.between(LocalDateTime.now(), user.getLastFail().plusMinutes(BLOCK_TIME));
            throw new DisabledException(authorizationUserService.getWaitTime(duration));
        }

        authorizationUserService.setDefaultAttempt(user);

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
