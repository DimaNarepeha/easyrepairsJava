/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.model.Notification;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.NotificationRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of Notification service
 * to send, set as seen notifications via UI.
 *
 * @author Dmytro Narepekha
 */
@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(final UserRepository userRepository,
                                   final NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void notifyByUserId(final Integer id, final Notification notification) {
        User userToNotify = userRepository.findById(id);//TODO add throw here
        List<Notification> userNotifications = userToNotify.getNotifications();
        if (userNotifications == null) {
            userNotifications = new ArrayList<>();
            userToNotify.setNotifications(userNotifications);
        }
        userNotifications.add(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(final Integer id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return user.getNotifications();//TODO add throw here
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void setNotificationSeen(final Integer notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(value -> value.setSeen(true));//TODO add throw here
    }
}
