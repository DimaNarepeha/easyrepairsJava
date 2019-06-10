/*
 *Open source 2019
 */
package com.softserve.demo.service;

import com.softserve.demo.model.Notification;

import java.util.List;

/**
 * Notification service
 * to send notifications via UI.
 *
 * @author Dmytro Narepekha
 */
public interface NotificationService {
    /**
     * Simply notifies user by id.
     * You don't need to set time.
     * It will be set inside the method.
     *
     * @param userId       id of user to notify
     * @param notification notification for user
     */
    void notifyByUserId(Integer userId, Notification notification);

    List<Notification> getNotificationsByUserId(Integer userId);

    /**
     * Set notification as seen by user.
     *
     * @param notificationId id of notification to be set as seen.
     */
    void setNotificationSeen(Integer notificationId);
}

