/*
 *Open source 2019
 */
package com.softserve.demo.service;

import com.softserve.demo.model.Notification;

/**
 * Notification service
 * to send notifications via UI.
 *
 * @author Dmytro Narepekha
 */
public interface NotificationService {
    void notifyByUserId(Integer id, Notification notification);
}
