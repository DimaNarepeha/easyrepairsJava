package com.softserve.demo.service.impl;


import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.AuthorizationUserService;
import com.softserve.demo.util.AuthorizationUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, AuthorizationUserService {

    private final UserRepository userRepository;

    private final AuthorizationUserMapper authorizationUserMapper;

    public UserServiceImpl(UserRepository userRepository, AuthorizationUserMapper authorizationUserMapper) {
        this.userRepository = userRepository;
        this.authorizationUserMapper = authorizationUserMapper;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = userRepository.findByEmail(username).orElseThrow(() ->
                    new UsernameNotFoundException("Invalid username or password."));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    private Set<? extends GrantedAuthority> getAuthority(User user) {
        return user.getRoles();
    }

    @Override
    public AuthorizationUserDTO getUser(String username) {
        User user = userRepository.findByUsername(username);
        return authorizationUserMapper.userToAuthorizationUserDTO(user);
    }

}
