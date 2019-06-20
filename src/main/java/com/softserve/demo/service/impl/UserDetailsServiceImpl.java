package com.softserve.demo.service.impl;

import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String INVALID_MSG = "Wrong login or password!!!";
    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException(INVALID_MSG));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    private Set<? extends GrantedAuthority> getAuthority(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getAuthority()))
                .collect(Collectors.toSet());
    }
}
