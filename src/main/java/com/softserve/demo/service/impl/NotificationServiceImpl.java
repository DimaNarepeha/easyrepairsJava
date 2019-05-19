/*
 *Open source 2019
 */
package com.softserve.demo.service.impl;

import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Notification;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.NotificationRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User userToNotify = userRepository.findById(id);
        if (userToNotify == null) {
            throw new NotFoundException("User was not found!");
        }
        userToNotify.getNotifications().add(notification);
        log.debug("User with id " + id + " was notified");
    }

    @Override
    public List<Notification> getNotificationsByUserId(final Integer id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("User was not found!");
        }
        return user.getNotifications();
    }

    /**
     * This method set the notification status as seen.
     *
     * @param notificationId id of notification to be set as seen.
     */
    @Override
    public void setNotificationSeen(final Integer notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (!notification.isPresent()) {
            throw new NotFoundException("Notification was not found!");
        }
        notification.ifPresent(value -> value.setSeen(true));
    }
}
