package com.softserve.demo.service.impl;

import com.softserve.demo.model.Notification;
import com.softserve.demo.model.User;
import com.softserve.demo.repository.UserRepository;
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
    private User user;
    private NotificationService notificationService;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setNotifications(new ArrayList<>());
        Mockito.when(userRepository.findById(1)).thenReturn(user);
        notificationService = new NotificationServiceImpl(userRepository);
    }

    @Test
    public void notifyByUserIdShouldWork() {
        Notification notification = new Notification();
        notificationService.notifyByUserId(1, notification);
        Assert.assertTrue(user.getNotifications().contains(notification));
    }
}
