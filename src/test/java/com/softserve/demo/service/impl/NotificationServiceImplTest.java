package com.softserve.demo.service.impl;

import com.softserve.demo.model.Notification;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.NotificationRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.NotificationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private EmailService emailService;
    private User user;
    private NotificationService notificationService;
    private Notification notification;

    @Before
    public void setUp() {
        notification = new Notification();
        user = new User();
        user.setId(1);
        user.setNotifications(new ArrayList<>());
        Mockito.when(userRepository.findById(1)).thenReturn(user);
        Mockito.when(notificationRepository.save(notification)).thenReturn(notification);
        notificationService = new NotificationServiceImpl(userRepository, notificationRepository, emailService);
    }

    @Test
    public void notifyByUserIdShouldWork() {
        notificationService.notifyByUserId(1, notification);
        Assert.assertTrue(user.getNotifications().contains(notification));
    }
}
