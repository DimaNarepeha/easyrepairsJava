/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.model.Notification;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Notification service
 * to send notifications via UI.
 *
 * @author Dmytro Narepekha
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private final UserRepository userRepository;

    public NotificationServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void notifyByUserId(final Integer id, final Notification notification) {
        User userToNotify = userRepository.findById(id);
        List<Notification> userNotifications = userToNotify.getNotifications();
        if (userNotifications == null) {
            userNotifications = new ArrayList<>();
            userToNotify.setNotifications(userNotifications);
        }
        userNotifications.add(notification);
    }
}
