package com.softserve.demo.service;

import com.softserve.demo.model.Notification;

public interface NotificationService {
    void notifyByUserId(Integer id, Notification notification);
}
