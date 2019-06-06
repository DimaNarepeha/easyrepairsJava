package com.softserve.demo.service.impl;

import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.RecoveryPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class PasswordRecoveryServiceImpl implements RecoveryPasswordService {

    private static final String SUBJECT = "Password recovery";
    private static final String MSG_TEXT = "Dear, %s! \n " +
            "Your new password is %s \n " +
            "Best regards, EasyRepairs.com";
    private static final String INVALID_EMAIL_MSG = "User with email: [%s] doesn`t exist";

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordRecoveryServiceImpl(UserRepository userRepository, EmailService emailService,
                                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void passwordRecovery(String email) {
        User userDB = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(String.format(INVALID_EMAIL_MSG, email)));

        String newPassword = generateNewPassword();
        emailService.sendEmailTo(userDB.getEmail(), SUBJECT,
                String.format(MSG_TEXT, userDB.getUsername(), newPassword));
        userDB.setPassword(passwordEncoder.encode(newPassword));
    }

    private String generateNewPassword() {
        return new Random().ints(16, 45, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
