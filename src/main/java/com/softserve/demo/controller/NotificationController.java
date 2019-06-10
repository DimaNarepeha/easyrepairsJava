package com.softserve.demo.controller;

import com.softserve.demo.dto.NotificationDTO;
import com.softserve.demo.model.Notification;
import com.softserve.demo.service.NotificationService;
import com.softserve.demo.util.mappers.NotificationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
@PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'PROVIDER')")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(final NotificationService notificationService,
                                  final NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping(path = "/get/{userId}")
    public List<NotificationDTO> getNotifications(@PathVariable final Integer userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return notificationMapper.notificationsToNotificationDTOs(notifications);
    }

    @PostMapping(path = "/add/{userId}")
    public NotificationDTO notifyUserById(@PathVariable final Integer userId,
                                          @RequestBody final NotificationDTO notificationDTO) {
        notificationService.notifyByUserId(userId, notificationMapper.notificationDTOToNotification(notificationDTO));
        return notificationDTO;
    }

    @PutMapping(path = "/{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setNotificationAsSeen(@PathVariable final Integer notificationId) {
        notificationService.setNotificationSeen(notificationId);
    }
}
