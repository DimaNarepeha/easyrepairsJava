package com.softserve.demo.service.impl;

import com.softserve.demo.dto.AuthorizationUserDTO;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.AuthorizationUserService;
import com.softserve.demo.util.AuthorizationUserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthorizationUserServiceImpl implements AuthorizationUserService {

    private static final int ATTEMPT_THRESHOLD = 5;
    private static final int BLOCK_TIME = 2;
    private static final int TIME_THRESH_HOLD = 1;
    private static final int BLOCK_TIME_IN_SECONDS = 120;
    private static final int DEFAULT_ATTEMPT_VALUE = 0;
    private static final int ONE_ATTEMPT = 1;
    private static final String BLOCK_MSG_MINUTES = "Your account blocked for %d minutes";
    private static final String BLOCK_MSG_SECONDS = "Your account blocked for %d seconds";
    private static final String WRONG_CREDENTIALS_MSG = "Wrong login or password!!!";
    private static final LocalDateTime DEFAULT_TIME_VALUE = null;

    private final UserRepository userRepository;
    private final AuthorizationUserMapper authorizationUserMapper;

    public AuthorizationUserServiceImpl(UserRepository userRepository,
                                        AuthorizationUserMapper authorizationUserMapper) {
        this.userRepository = userRepository;
        this.authorizationUserMapper = authorizationUserMapper;
    }

    @Override
    public AuthorizationUserDTO getUser(String username) {
        User user = userRepository.findByUsername(username).get();
        return authorizationUserMapper.userToAuthorizationUserDTO(user);
    }

    @Override
    public void checkAttempts(AuthorizationUserDTO userDTO) {
        if (userDTO.getAttempts() >= ATTEMPT_THRESHOLD) {
            userDTO.setLastFail(LocalDateTime.now());
            updateUser(userDTO);
            throw new DisabledException(String.format(BLOCK_MSG_MINUTES, BLOCK_TIME));
        } else {
            userDTO.setLastFail(LocalDateTime.now());
            userDTO.setAttempts(userDTO.getAttempts() + ONE_ATTEMPT);
            updateUser(userDTO);
            throw new BadCredentialsException(WRONG_CREDENTIALS_MSG);
        }
    }

    @Override
    public boolean checkLastFailTime(AuthorizationUserDTO userDTO) {
        LocalDateTime lastFail = userDTO.getLastFail();
        if (lastFail != null) {
            Duration duration = Duration.between(LocalDateTime.now(), lastFail);
            return Math.abs(duration.toMinutes()) < BLOCK_TIME;
        }
        return false;
    }

    @Override
    public String getWaitTime(Duration duration) {
        if (Math.abs(duration.toMinutes()) == TIME_THRESH_HOLD) {
            return String.format(BLOCK_MSG_SECONDS, BLOCK_TIME_IN_SECONDS - Math.abs(duration.getSeconds()));
        }
        return String.format(BLOCK_MSG_MINUTES, BLOCK_TIME - Math.abs(duration.toMinutes()));
    }

    @Override
    public void setDefaultAttempt(AuthorizationUserDTO user) {
        user.setAttempts(DEFAULT_ATTEMPT_VALUE);
        user.setLastFail(DEFAULT_TIME_VALUE);
        updateUser(user);
    }

    @Override
    public void updateUser(AuthorizationUserDTO userDTO) {
        Optional<User> userDB = userRepository.findByUsername(userDTO.getUserLogin());
        if (userDB.isPresent()) {
            userDB.get().setLastFail(userDTO.getLastFail());
            userDB.get().setAttempts(userDTO.getAttempts());
            userRepository.save(userDB.get());
        }
    }
}
