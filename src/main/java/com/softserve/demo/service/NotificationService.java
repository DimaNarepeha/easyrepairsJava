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

    void notifyByUserId(Integer id, Notification notification);

    List<Notification> getNotificationsByUserId(Integer userId);

    /**
     * Set notification as seen by user.
     *
     * @param notificationId id of notification to be set as seen.
     */
    void setNotificationSeen(Integer notificationId);
}

